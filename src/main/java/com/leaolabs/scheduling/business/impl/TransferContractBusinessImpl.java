package com.leaolabs.scheduling.business.impl;

import com.leaolabs.scheduling.business.TransferContractBusiness;
import com.leaolabs.scheduling.model.Customer;
import com.leaolabs.scheduling.model.Scheduling;
import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.repository.CustomerRepository;
import com.leaolabs.scheduling.repository.SchedulingRepository;
import com.leaolabs.scheduling.repository.TransferContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class TransferContractBusinessImpl implements TransferContractBusiness {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private TransferContractRepository transferContractRepository;

    @Override
    public Optional<TransferContract> create(final TransferContract transferContract) {
        Customer customer = getCustomer(transferContract);
        Scheduling scheduling = this.schedulingRepository.save(Scheduling.builder()
                .schedulingDate(ZonedDateTime.now())
                .transferDate(transferContract.getScheduling().getTransferDate())
                .build());

        transferContract.setScheduling(scheduling);
        transferContract.setCustomer(customer);

        return Optional.of(this.transferContractRepository.save(transferContract));
    }

    private Customer getCustomer(final TransferContract transferContract) {
        final String cpf = transferContract.getCustomer().getCpf();

        var optionalCustomer = this.customerRepository.findByCpf(cpf);
        var customer = new Customer();

        if (optionalCustomer.isEmpty()) {
            customer = this.customerRepository.save(Customer.builder()
                    .name(transferContract.getCustomer().getName())
                    .cpf(transferContract.getCustomer().getCpf())
                    .build());
        } else {
            customer = optionalCustomer.get();
        }
        return customer;
    }

    @Override
    public Optional<TransferContract> findByCustomerCpf(String cpf) {
        return Optional.empty();
    }
}
