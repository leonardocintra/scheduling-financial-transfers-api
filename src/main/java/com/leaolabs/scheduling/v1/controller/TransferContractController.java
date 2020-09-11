package com.leaolabs.scheduling.v1.controller;

import com.leaolabs.scheduling.business.TransferContractBusiness;
import com.leaolabs.scheduling.commons.controller.BaseController;
import com.leaolabs.scheduling.commons.controller.ResponseMeta;
import com.leaolabs.scheduling.commons.exception.EntityNotFoundException;
import com.leaolabs.scheduling.v1.dtos.CustomerDto;
import com.leaolabs.scheduling.v1.dtos.TransferContractDto;
import com.leaolabs.scheduling.v1.mapper.TransferContractMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/scheduling/transfer")
public class TransferContractController extends BaseController {

    private final TransferContractBusiness transferContractBusiness;
    private final TransferContractMapper transferContractMapper;

    public TransferContractController(final TransferContractBusiness transferContractBusiness, final TransferContractMapper transferContractMapper) {
        this.transferContractBusiness = transferContractBusiness;
        this.transferContractMapper = transferContractMapper;
    }

    @ResponseBody
    @GetMapping(value = "/customer")
    public ResponseEntity<ResponseMeta> findByCustomerCpf(@RequestParam(value = "cpf", required = true) String cpf) {
        final var optionalTransferContract =
                this.transferContractBusiness.findByCustomerCpf(cpf);

        final var transferContractDto =
                this.transferContractMapper.serialize(optionalTransferContract.get());

        return super.buildResponse(HttpStatus.OK, Optional.of(transferContractDto));
    }

    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMeta> post(@RequestBody @Valid final TransferContractDto transferContractDto) {
        final var optionalTransfer = this.transferContractBusiness.create(this.transferContractMapper.deserialize(transferContractDto));

        return super.buildResponse(HttpStatus.CREATED,
                Optional.of(this.transferContractMapper.serialize(optionalTransfer.orElseThrow(() -> new EntityNotFoundException("Transfer Contract")))));
    }

}
