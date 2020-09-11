package com.leaolabs.scheduling.v1.dtos;

import com.leaolabs.scheduling.model.Customer;
import com.leaolabs.scheduling.model.Scheduling;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TransferContractDto implements Serializable {

    private Long id;

    private String accountOrigin;

    private String accountTarget;

    private BigDecimal amount;

    private Customer customer;

    private Scheduling scheduling;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
