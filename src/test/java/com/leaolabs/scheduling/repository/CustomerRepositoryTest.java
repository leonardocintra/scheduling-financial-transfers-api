package com.leaolabs.scheduling.repository;

import com.leaolabs.scheduling.model.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByCpf() {
        this.customerRepository.save(Customer.builder()
                .name("Customer Of Test")
                .cpf("11122233345")
                .build());

        Optional<Customer> result = this.customerRepository.findByCpf("11122233345");

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.get().getId());
        Assert.assertEquals(result.get().getName(), "Customer Of Test");
    }

    @Test
    public void whenFindByCpfNotFound() {
        Optional<Customer> result = this.customerRepository.findByCpf("10022233345");

        Assert.assertTrue(result.isEmpty());
    }
}
