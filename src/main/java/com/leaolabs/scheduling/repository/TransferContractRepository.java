package com.leaolabs.scheduling.repository;

import com.leaolabs.scheduling.model.TransferContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferContractRepository extends JpaRepository<TransferContract, Long> {
}
