package com.leaolabs.scheduling.v1.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Builder
public class TaxDto {
    private Long id;
    private BigDecimal amount;
    private String taxDescription;
}
