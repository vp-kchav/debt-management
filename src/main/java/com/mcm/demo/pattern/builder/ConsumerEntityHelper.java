package com.mcm.demo.pattern.builder;


import com.mcm.demo.adapter.persistence.entity.BankAccountEntity;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.entity.CreditCardAccountEntity;
import com.mcm.demo.adapter.persistence.entity.MortgageAccountEntity;
import com.mcm.demo.model.DebtType;
import com.mcm.demo.model.request.Debt;

/**
 * @author kchav
 */

public enum ConsumerEntityHelper {

   INSTANCE;

    public void addDebtEntity(ConsumerEntity entity, Debt debt) {
        if(debt.getDescription() != null) {
            if (DebtType.BankAccount.equals(DebtType.getDebtTypeValue(debt.getDescription().getAccountType()))) {
                BankAccountBuilder bankAccountBuilder = new BankAccountBuilder(debt);
                BankAccountEntity bankAccountEntity = bankAccountBuilder.buildBankAccountEntity(entity);
                entity.getDebts().add(bankAccountEntity);
            }
            if (DebtType.CreditCardAccount.equals(DebtType.getDebtTypeValue(debt.getDescription().getAccountType()))) {
                CreditCardAccountBuilder creditCardAccountBuilder = new CreditCardAccountBuilder(debt);
                CreditCardAccountEntity creditCardAccountEntity = creditCardAccountBuilder.buildCreditCardAccountEntity(entity);
                entity.getDebts().add(creditCardAccountEntity);
            }
            if (DebtType.MortgageAccount.equals(DebtType.getDebtTypeValue(debt.getDescription().getAccountType()))) {
                MortgageAccountBuilder mortgageAccountBuilder = new MortgageAccountBuilder(debt);
                MortgageAccountEntity mortgageAccountEntity = mortgageAccountBuilder.buildMortgageAccountEntity(entity);
                entity.getDebts().add(mortgageAccountEntity);
            }
        }
    }
}
