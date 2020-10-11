package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * @author kchav
 */
@Entity
@Getter
@Setter
@DiscriminatorValue("Mortgage_Account")
public class MortgageAccountEntity extends BankAccountEntity {


    public MortgageAccountEntity(String accountNumber, float amount, ConsumerEntity consumer) {
        super(accountNumber,amount,consumer);
    }

    public MortgageAccountEntity() {

    }

}
