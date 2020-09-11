package com.leaolabs.scheduling.v1.controller;

import com.leaolabs.scheduling.business.TransferContractBusiness;
import com.leaolabs.scheduling.v1.mapper.TransferContractMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/scheduling/transfer")
public class TransferContractController {

    private final TransferContractBusiness transferContractBusiness;
    private final TransferContractMapper transferContractMapper;

    public TransferContractController(final TransferContractBusiness transferContractBusiness, final TransferContractMapper transferContractMapper) {
        this.transferContractBusiness = transferContractBusiness;
        this.transferContractMapper = transferContractMapper;
    }

}
