package com.leaolabs.scheduling.v1.dtos;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class TransferContractDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String accountOrigin;

    @NotBlank
    @Size(max = 100)
    private String accountTarget;

    @DecimalMin(value = "1.00")
    @NotNull
    private BigDecimal amount;

    private CustomerDto customer;

    private SchedulingDto scheduling;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
