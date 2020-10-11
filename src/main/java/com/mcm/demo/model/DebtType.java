package com.mcm.demo.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author kchav
 */
public enum DebtType {
    CreditCardAccount(Arrays.asList("CREDITCARD")),
    BankAccount(Arrays.asList("LOAN")),
    MortgageAccount(Arrays.asList("FIXED-30-YEAR","FIXED-15-YEAR","FIXED-10-YEAR"));

    final private List<String> labels;

    private DebtType(List<String> labels) {
        this.labels = labels;
    }

    public static DebtType getDebtTypeValue(String value) {
        for(DebtType debtType  : values()){
            if(debtType.labels.contains(StringUtils.upperCase(value.trim())))
                return debtType;
        }
        return null;
    }
}
