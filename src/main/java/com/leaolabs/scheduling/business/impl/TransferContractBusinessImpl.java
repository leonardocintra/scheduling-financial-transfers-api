package com.leaolabs.scheduling.business.impl;

import com.leaolabs.scheduling.business.TransferContractBusiness;
import com.leaolabs.scheduling.model.TransferContract;

import java.util.Optional;

public class TransferContractBusinessImpl implements TransferContractBusiness {
    @Override
    public Optional<TransferContract> create(TransferContract transferContract) {
        return Optional.empty();
    }

    @Override
    public Optional<TransferContract> findByCustomerCpf(String cpf) {
        return Optional.empty();
    }
}
