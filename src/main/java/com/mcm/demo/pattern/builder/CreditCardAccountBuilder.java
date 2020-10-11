package com.mcm.demo.pattern.builder;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.entity.CreditCardAccountEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.request.Debt;
import com.mcm.demo.utils.DateTimeUtils;


/**
 * @author kchav
 */
public class CreditCardAccountBuilder {

    private Debt debt;

    public CreditCardAccountBuilder(final Debt debt) {
       this.debt = debt;
    }

    /**
     *
     * @param entity
     * @return
     */
    public CreditCardAccountEntity buildCreditCardAccountEntity(final ConsumerEntity entity) {
        CreditCardAccountEntity creditCardAccountEntity = new CreditCardAccountEntity(debt.getAccountNumber(),
                debt.getAmount(),entity);
        if(debt.getDescription() != null) {
            creditCardAccountEntity.setExpirationDate(DateTimeUtils.INSTANCE.getDateFromString(debt.getDescription().getExpirationDate(), ApplicationConstants.DATE_FORMAT));
            creditCardAccountEntity.setAccountType(debt.getDescription().getAccountType());
            creditCardAccountEntity.setIssuer(debt.getDescription().getIssuer());
            creditCardAccountEntity.setCreditCardNumber(debt.getDescription().getCreditCardNumber());
        }
        return creditCardAccountEntity;
    }
}
