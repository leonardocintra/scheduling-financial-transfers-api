package com.leaolabs.scheduling.v1.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
public class SchedulingDto implements Serializable {

    private Long id;

    private ZonedDateTime schedulingDate;

    private LocalDate transferDate;

}
