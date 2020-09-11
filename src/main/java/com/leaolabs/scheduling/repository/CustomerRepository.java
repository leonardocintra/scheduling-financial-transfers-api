package com.leaolabs.scheduling.repository;

import com.leaolabs.scheduling.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query
    Optional<Customer> findByCpf(String cpf);
}
