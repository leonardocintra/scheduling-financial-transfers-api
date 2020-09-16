package com.leaolabs.scheduling.repository;

import com.leaolabs.scheduling.model.Customer;
import com.leaolabs.scheduling.model.Scheduling;
import com.leaolabs.scheduling.model.Tax;
import com.leaolabs.scheduling.model.TransferContract;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class TransferContractRepositoryTest {

    @Autowired
    private TransferContractRepository transferContractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Test
    public void whenFindByCustomerId() {
        var customer = this.customerRepository.save(Customer.builder()
                .name("Customer Of Test")
                .cpf("11122233345")
                .build());

        var scheduling = this.schedulingRepository.save(Scheduling.builder().transferDate(LocalDate.now()).build());
        var tax = this.taxRepository.save(Tax.builder()
                .amount(BigDecimal.valueOf(10))
                .taxDescription("Taxa padrao")
                .build());

        this.transferContractRepository.save(TransferContract.builder()
                .amount(BigDecimal.valueOf(100.00))
                .scheduling(scheduling)
                .tax(tax)
                .accountTarget("123")
                .accountOrigin("123")
                .customer(customer)
                .totalPaid(BigDecimal.valueOf(110.00))
                .build());

        var result = this.transferContractRepository.findByCustomerId(customer.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.get().get(0).getCustomer().getName(), "Customer Of Test");
        Assert.assertEquals(result.get().get(0).getCustomer().getCpf(), "11122233345");
    }
}
