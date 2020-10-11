package com.mcm.demo.pattern.builder;


import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.entity.MortgageAccountEntity;
import com.mcm.demo.model.request.Debt;

/**
 * @author kchav
 */
public class MortgageAccountBuilder {
    private Debt debt;

    public MortgageAccountBuilder(final Debt debt) {
        this.debt = debt;
    }

    /**
     *
     * @param entity
     * @return
     */
    public MortgageAccountEntity buildMortgageAccountEntity(final ConsumerEntity entity) {
        MortgageAccountEntity mortgageAccountEntity = new MortgageAccountEntity(debt.getAccountNumber(),
                debt.getAmount(),entity);
        if(debt.getDescription() != null) {
            mortgageAccountEntity.setAccountType(debt.getDescription().getAccountType());
            mortgageAccountEntity.setLenderName(debt.getDescription().getLenderName());
        }
        return mortgageAccountEntity;
    }
}
