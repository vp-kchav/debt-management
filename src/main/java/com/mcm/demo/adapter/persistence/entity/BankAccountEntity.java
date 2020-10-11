package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * @author kchav
 */
@Entity
@DiscriminatorValue("Bank_Account")
@Getter
@Setter
public class BankAccountEntity extends DebtEntity {

    @Column(name = "lender_name")
    private String lenderName;

    public BankAccountEntity(String accountNumber, float amount, ConsumerEntity consumer) {
        super(accountNumber,amount,consumer);
    }

    public BankAccountEntity() {

    }
}
