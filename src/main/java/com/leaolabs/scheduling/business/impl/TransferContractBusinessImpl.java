package com.leaolabs.scheduling.business.impl;

import com.leaolabs.scheduling.business.TaxOperation;
import com.leaolabs.scheduling.business.TransferContractBusiness;
import com.leaolabs.scheduling.commons.exception.EntityNotFoundException;
import com.leaolabs.scheduling.model.Customer;
import com.leaolabs.scheduling.model.Scheduling;
import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.repository.CustomerRepository;
import com.leaolabs.scheduling.repository.SchedulingRepository;
import com.leaolabs.scheduling.repository.TransferContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferContractBusinessImpl implements TransferContractBusiness {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private TransferContractRepository transferContractRepository;
    @Autowired
    private TaxOperation taxOperation;

    @Override
    public Optional<TransferContract> create(final TransferContract transferContract) {
        Customer customer = getCustomer(transferContract);
        Scheduling scheduling = this.schedulingRepository.save(Scheduling.builder()
                .schedulingDate(ZonedDateTime.now())
                .transferDate(transferContract.getScheduling().getTransferDate())
                .build());

        var tax = this.taxOperation.getTax(transferContract);
        var totalPaid = Double.parseDouble(tax.getAmount().toString())
                + Double.parseDouble(transferContract.getAmount().toString());

        transferContract.setScheduling(scheduling);
        transferContract.setCustomer(customer);
        transferContract.setTax(tax);
        transferContract.setTotalPaid(BigDecimal.valueOf(totalPaid));

        return Optional.of(this.transferContractRepository.save(transferContract));
    }

    @Override
    public Optional<List<TransferContract>> findByCustomerCpf(String cpf) {
        var optionalCustomer = this.customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Customer"));

        return Optional.of(this.transferContractRepository.findByCustomerId(optionalCustomer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Transfer Contract")));
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
}
