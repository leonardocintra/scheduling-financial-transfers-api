package com.leaolabs.scheduling.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduling_date", nullable = false)
    private ZonedDateTime schedulingDate;

    @Column(name = "transfer_date", nullable = false)
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
