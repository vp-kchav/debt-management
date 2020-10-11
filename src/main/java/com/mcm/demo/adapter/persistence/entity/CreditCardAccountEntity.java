package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;


/**
 * @author kchav
 */
@Entity
@Getter
@Setter
@DiscriminatorValue("Credit_Card")
public class CreditCardAccountEntity extends DebtEntity {

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "credit_Card_Number")
    private String creditCardNumber;

    public CreditCardAccountEntity(final String accountNumber, final float amount, final ConsumerEntity consumer) {
        super(accountNumber,amount,consumer);
    }

    public CreditCardAccountEntity() {

    }
}
