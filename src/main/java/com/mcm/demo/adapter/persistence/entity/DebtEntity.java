package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Debt Entity.
 *
 * @author kchav
 */
@Entity
@Table(name = "debt")
@Setter
@Getter
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "note_seq")
public class DebtEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "amount", nullable = false)
    private float amount;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private ConsumerEntity consumer;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    public DebtEntity(final String accountNumber, final float amount, final ConsumerEntity consumer,final String accountType) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.consumer = consumer;
        this.accountType = accountType;
    }

    public DebtEntity(final String accountNumber, final float amount, final ConsumerEntity consumer) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.consumer = consumer;
    }

    public DebtEntity() {

    }

}
