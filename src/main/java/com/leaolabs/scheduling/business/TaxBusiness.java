package com.leaolabs.scheduling.business;

import com.leaolabs.scheduling.model.Tax;
import com.leaolabs.scheduling.model.TransferContract;

public interface TaxBusiness {
    Tax caculate(TransferContract transferContract);

    Tax create(Tax tax);
}
