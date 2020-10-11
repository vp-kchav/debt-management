package com.mcm.demo.model.output;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Debt Entity.
 *
 * @author kchav
 */
@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class DebtOutput {

    private float amount;

    private String accountType;

}
