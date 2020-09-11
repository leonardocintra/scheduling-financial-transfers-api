package com.leaolabs.scheduling.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @Size(max = 11)
    @Size(min = 11)
    @Column(nullable = false)
    private String cpf;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = updatedAt = ZonedDateTime.now();
    }
}
