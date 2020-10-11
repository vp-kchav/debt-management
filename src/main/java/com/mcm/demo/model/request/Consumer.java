package com.mcm.demo.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kchav
 */
@Setter
@Getter
@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString(callSuper=true, includeFieldNames=true)
public class Consumer {

    @XmlElement
    private String consumerId;
    @XmlElement
    private String consumerName;

    @XmlElements({
            @XmlElement(name="debts"),
            @XmlElement(name="bankAccount"),
            @XmlElement(name="creditCardAccount"),
            @XmlElement(name="mortgageAccount")
    })
//    private Map<String,Debt> debts = new HashMap<>();
    private List<Debt> debts = new ArrayList<>();
}
