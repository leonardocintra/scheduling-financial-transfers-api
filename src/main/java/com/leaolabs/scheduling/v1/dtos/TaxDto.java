package com.leaolabs.scheduling.v1.dtos;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Builder
public class TaxDto {
    private Long id;
    private BigDecimal amount;
    private String taxDescription;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
