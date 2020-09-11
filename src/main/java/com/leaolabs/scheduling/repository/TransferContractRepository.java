package com.leaolabs.scheduling.repository;

import com.leaolabs.scheduling.model.TransferContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransferContractRepository extends JpaRepository<TransferContract, Long> {

    @Query
    Optional<List<TransferContract>> findByCustomerId(Long id);
}
