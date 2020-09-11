package com.leaolabs.scheduling.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class Tax {

    private BigDecimal amount;

    @Size(max = 20)
    private String percentage;

}
