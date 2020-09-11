package com.leaolabs.scheduling.v1.mapper;

import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.v1.dtos.TransferContractDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransferContractMapper {
    public TransferContractDto serialize(final TransferContract transferContract) {
        if (Optional.ofNullable(transferContract).isEmpty()) {
            return new TransferContractDto();
        }

        return TransferContractDto.builder()
                .id(transferContract.getId())
                .accountOrigin(transferContract.getAccountOrigin())
                .accountTarget(transferContract.getAccountTarget())
                .amount(transferContract.getAmount())
                .customer(transferContract.getCustomer())
                .scheduling(transferContract.getScheduling())
                .updatedAt(transferContract.getUpdatedAt())
                .createdAt(transferContract.getCreatedAt())
                .build();
    }

    public List<TransferContractDto> serialize(final List<TransferContract> transferContracts) {
        return Optional.ofNullable(transferContracts)
                .map(transferContracts1 -> transferContracts.stream()
                        .map(this::serialize).collect(Collectors.toList())).orElse(null);
    }

    public TransferContract deserialize(final TransferContractDto transferContractDto) {
        if (Optional.ofNullable(transferContractDto).isEmpty()) {
            return new TransferContract();
        }

        return TransferContract.builder()
                .id(transferContractDto.getId())
                .accountOrigin(transferContractDto.getAccountOrigin())
                .accountTarget(transferContractDto.getAccountTarget())
                .amount(transferContractDto.getAmount())
                .customer(transferContractDto.getCustomer())
                .scheduling(transferContractDto.getScheduling())
                .createdAt(transferContractDto.getCreatedAt())
                .updatedAt(transferContractDto.getUpdatedAt())
                .build();
    }

    public List<TransferContract> deserialize(final List<TransferContractDto> transferContractDtos) {
        return Optional.ofNullable(transferContractDtos)
                .map(transfer -> transfer.stream()
                        .map(this::deserialize).collect(Collectors.toList())).orElse(null);
    }
}
