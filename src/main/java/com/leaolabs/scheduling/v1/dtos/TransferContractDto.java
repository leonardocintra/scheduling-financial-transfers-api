package com.leaolabs.scheduling.v1.dtos;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferContractDto implements Serializable {

    private Long id;

    private String accountOrigin;

    private String accountTarget;

    private BigDecimal amount;

    private CustomerDto customer;

    private SchedulingDto scheduling;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
