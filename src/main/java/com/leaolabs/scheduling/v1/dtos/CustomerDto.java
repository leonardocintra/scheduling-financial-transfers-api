package com.leaolabs.scheduling.v1.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 11, min = 11)
    private String cpf;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
