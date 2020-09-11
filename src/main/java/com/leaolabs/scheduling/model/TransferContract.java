package com.leaolabs.scheduling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "account")
public class TransferContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10)
    @Column(name = "account_origin", nullable = false)
    private String accountOrigin;

    @Size(max = 10)
    @Column(name = "account_target", nullable = false)
    private String accountTarget;

    @Column(nullable = false)
    private BigDecimal amount;

    @OneToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @OneToOne(optional = false)
    @JoinColumn(name = "scheduling_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scheduling scheduling;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = updatedAt = ZonedDateTime.now();
    }
}
