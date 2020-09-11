package com.leaolabs.scheduling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduling_date")
    private ZonedDateTime schedulingDate;

    @Column(name = "transfer_date")
    private LocalDate transferDate;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        schedulingDate = createdAt = updatedAt = ZonedDateTime.now();
    }
}
