package com.leaolabs.scheduling.v1.mapper;

import com.leaolabs.scheduling.model.Customer;
import com.leaolabs.scheduling.model.Scheduling;
import com.leaolabs.scheduling.model.TransferContract;
import com.leaolabs.scheduling.v1.dtos.CustomerDto;
import com.leaolabs.scheduling.v1.dtos.SchedulingDto;
import com.leaolabs.scheduling.v1.dtos.TaxDto;
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
                .totalPaid(transferContract.getTotalPaid())
                .customer(CustomerDto.builder()
                        .id(transferContract.getCustomer().getId())
                        .cpf(transferContract.getCustomer().getCpf())
                        .name(transferContract.getCustomer().getName())
                        .createdAt(transferContract.getCustomer().getCreatedAt())
                        .updatedAt(transferContract.getCustomer().getUpdatedAt())
                        .build())
                .scheduling(SchedulingDto.builder()
                        .id(transferContract.getScheduling().getId())
                        .schedulingDate(transferContract.getScheduling().getSchedulingDate())
                        .transferDate(transferContract.getScheduling().getTransferDate())
                        .createdAt(transferContract.getScheduling().getCreatedAt())
                        .updatedAt(transferContract.getScheduling().getUpdatedAt())
                        .build())
                .tax(TaxDto.builder()
                        .id(transferContract.getTax().getId())
                        .amount(transferContract.getTax().getAmount())
                        .taxDescription(transferContract.getTax().getTaxDescription())
                        .createdAt(transferContract.getTax().getCreatedAt())
                        .updatedAt(transferContract.getTax().getUpdatedAt())
                        .build())
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
                .customer(Customer.builder()
                        .id(transferContractDto.getCustomer().getId())
                        .cpf(transferContractDto.getCustomer().getCpf())
                        .name(transferContractDto.getCustomer().getName())
                        .createdAt(transferContractDto.getCreatedAt())
                        .updatedAt(transferContractDto.getUpdatedAt())
                        .build())
                .scheduling(Scheduling.builder()
                        .id(transferContractDto.getScheduling().getId())
                        .schedulingDate(transferContractDto.getScheduling().getSchedulingDate())
                        .transferDate(transferContractDto.getScheduling().getTransferDate())
                        .createdAt(transferContractDto.getCreatedAt())
                        .updatedAt(transferContractDto.getUpdatedAt())
                        .build())
                .createdAt(transferContractDto.getCreatedAt())
                .updatedAt(transferContractDto.getUpdatedAt())
                .build();
    }
}
