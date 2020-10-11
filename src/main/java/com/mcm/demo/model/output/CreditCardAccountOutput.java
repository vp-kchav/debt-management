package com.mcm.demo.model.output;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * @author kchav
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCardAccountOutput extends DebtOutput {

    private String expirationDate;

    private String issuer;

    private String creditCardNumber;

}
