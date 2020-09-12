package com.leaolabs.scheduling.v1.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class SchedulingDto implements Serializable {

    private Long id;

    private ZonedDateTime schedulingDate;

    private LocalDate transferDate;

}
