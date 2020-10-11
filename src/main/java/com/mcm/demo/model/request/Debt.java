package com.mcm.demo.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * @author kchav
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@ToString(callSuper=true, includeFieldNames=true)
public class Debt {

    @XmlElement
    private String accountNumber;
    @XmlElement
    private float amount;
    @XmlElement
    private Description description;

}
