package com.leaolabs.scheduling.business;

import com.leaolabs.scheduling.model.TransferContract;

import java.util.Optional;

public interface TransferContractBusiness {

    Optional<TransferContract> create(TransferContract transferContract);

    Optional<TransferContract> findByCustomerCpf(String cpf);
}
