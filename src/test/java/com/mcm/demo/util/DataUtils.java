package com.mcm.demo.util;

import com.mcm.demo.model.request.Consumer;
import com.mcm.demo.model.request.Debt;
import com.mcm.demo.model.request.Description;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kchav
 */
public class DataUtils {

    public static Consumer getConsumer() {
        Consumer consumer = new Consumer();
        consumer.setConsumerId("kimtey123");
        consumer.setConsumerName("Kimtety Debt");
        consumer.setDebts(getDebts());
        return consumer;
    }

    public static List<Debt> getDebts() {
        List<Debt> debts = new ArrayList<>();
        debts.add(getDebtBank());debts.add(getDebtCredit());debts.add(getDebtMortgage());
        return debts;
    }

    public static Debt getDebtBank(){
        Debt debt1 = new Debt();
        debt1.setAccountNumber("account 01");
        debt1.setAmount(123);
        debt1.setDescription(getDescriptionBank());
        return debt1;
    }

    public static Debt getDebtCredit(){
        Debt debt2 = new Debt();
        debt2.setAccountNumber("account 02");
        debt2.setAmount(456);
        debt2.setDescription(getDescriptionCreditCard());
        return debt2;
    }

    public static Debt getDebtMortgage(){
        Debt debt3 = new Debt();
        debt3.setAccountNumber("account 03");
        debt3.setAmount(789);
        debt3.setDescription(getDescriptionMortgage());
        return debt3;
    }

    public static Description getDescriptionBank(){
        Description description = new Description();
        description.setAccountType("Loan");
        description.setLenderName("Chase");
        return  description;
    }

    public static Description getDescriptionCreditCard(){
        Description description = new Description();
        description.setAccountType("CREDITCARD");
        description.setIssuer("city");
        description.setCreditCardNumber("1234XXX2323");
        description.setExpirationDate("20222020");
        return description;
    }

    public static Description getDescriptionMortgage(){
        Description description = new Description();
        description.setAccountType("Fixed-30-year");
        description.setLenderName("Chase");
        return description;
    }
}
