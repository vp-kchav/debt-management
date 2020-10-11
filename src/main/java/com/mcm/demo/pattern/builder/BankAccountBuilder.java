package com.mcm.demo.pattern.builder;


import com.mcm.demo.adapter.persistence.entity.BankAccountEntity;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.model.request.Debt;

/**
 * @author kchav
 */

public class BankAccountBuilder {
    private Debt debt;

    public BankAccountBuilder(final Debt debt) {
        this.debt = debt;
    }

    /**
     *
     * @param entity
     * @return
     */
    public BankAccountEntity buildBankAccountEntity(final ConsumerEntity entity) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(debt.getAccountNumber(),
                debt.getAmount(),entity);
        if(debt.getDescription() != null) {
            bankAccountEntity.setAccountType(debt.getDescription().getAccountType());
            bankAccountEntity.setLenderName(debt.getDescription().getLenderName());
        }
        return bankAccountEntity;
    }

}
