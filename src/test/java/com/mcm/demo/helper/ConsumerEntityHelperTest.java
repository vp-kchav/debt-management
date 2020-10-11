package com.mcm.demo.helper;

import com.mcm.demo.adapter.persistence.entity.BankAccountEntity;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.entity.CreditCardAccountEntity;
import com.mcm.demo.adapter.persistence.entity.MortgageAccountEntity;
import com.mcm.demo.model.request.Debt;
import com.mcm.demo.pattern.builder.ConsumerEntityHelper;
import com.mcm.demo.util.DataUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author kchav
 */
class ConsumerEntityHelperTest {


    @BeforeEach
    public void setup() {

    }

    @Test
    void testAddDebtEntity_Bank() {
        Debt debt = DataUtils.getDebtBank();
        ConsumerEntity entity = new ConsumerEntity();
        ConsumerEntityHelper.INSTANCE.addDebtEntity(entity,debt);
        Assert.assertNotNull(entity.getDebts());
        Assert.assertTrue(entity.getDebts().iterator().next() instanceof BankAccountEntity);
    }

    @Test
    void testAddDebtEntity_CreditCard() {
        Debt debt = DataUtils.getDebtCredit();
        ConsumerEntity entity = new ConsumerEntity();
        ConsumerEntityHelper.INSTANCE.addDebtEntity(entity,debt);
        Assert.assertNotNull(entity.getDebts());
        Assert.assertTrue(entity.getDebts().iterator().next() instanceof CreditCardAccountEntity);
    }

    @Test
    void testAddDebtEntity_Mortgage() {
        Debt debt = DataUtils.getDebtMortgage();
        ConsumerEntity entity = new ConsumerEntity();
        ConsumerEntityHelper.INSTANCE.addDebtEntity(entity,debt);
        Assert.assertNotNull(entity.getDebts());
        Assert.assertTrue(entity.getDebts().iterator().next() instanceof MortgageAccountEntity);
    }


}
