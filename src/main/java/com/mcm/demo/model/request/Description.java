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
public class Description {
    @XmlElement
    private String accountType;
    @XmlElement
    private String lenderName;
    @XmlElement
    private String creditCardNumber;
    @XmlElement
    private String expirationDate;
    @XmlElement
    private String issuer;
}
