package com.leaolabs.scheduling.business;

import com.leaolabs.scheduling.model.TransferContract;

import java.util.List;
import java.util.Optional;

public interface TransferContractBusiness {

    Optional<TransferContract> create(TransferContract transferContract);

    Optional<List<TransferContract>> findByCustomerCpf(String cpf);
}
