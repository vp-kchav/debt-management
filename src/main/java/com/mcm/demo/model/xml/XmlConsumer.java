package com.mcm.demo.model.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kchav
 */
@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
public class XmlConsumer {

    @XmlElement
    private String consumerId;

    @XmlElement
    private String consumerName;

    @XmlElement
    private XmlDebt debts;

}
