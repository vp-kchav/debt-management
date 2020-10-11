package com.mcm.demo.model.output;

import com.mcm.demo.adapter.persistence.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper=true, includeFieldNames=true)
@Getter
@Setter
@XmlRootElement(name = "consumer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumerOutput {

    @XmlElement
    private String consumerId;

    @XmlElement
    private String consumerName;

    @XmlElements({
            @XmlElement(name="bankAccount", type = BankAccountOutput.class),
            @XmlElement(name="creditCardAccount",type = CreditCardAccountOutput.class),
            @XmlElement(name="mortgageAccount", type = MortgageAccountOutput.class)
    })
    private List<DebtOutput> debts = new ArrayList<>();
}
