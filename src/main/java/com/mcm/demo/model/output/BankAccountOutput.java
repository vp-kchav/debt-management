package com.mcm.demo.model.output;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author kchav
 */
@Getter
@Setter
@XmlRootElement(name = "bankAccount")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountOutput extends DebtOutput {
    private String lenderName;
}
