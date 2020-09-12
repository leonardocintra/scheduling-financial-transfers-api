package com.leaolabs.scheduling.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer_contract")
public class TransferContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_origin", nullable = false)
    private String accountOrigin;

    @Column(name = "account_target", nullable = false)
    private String accountTarget;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @OneToOne(optional = false)
    @JoinColumn(name = "scheduling_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Scheduling scheduling;

    //@OneToOne(optional = false)
    //@JoinColumn(name = "tax_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //private Tax tax;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = updatedAt = ZonedDateTime.now();
    }
}
