package com.mcm.demo.model.xml;

import com.mcm.demo.model.request.Debt;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;


/**
 * @author kchav
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@ToString(callSuper=true, includeFieldNames=true)
public class XmlDebt {

    @XmlElements({
            @XmlElement(name="debts"),
            @XmlElement(name="bankAccount"),
            @XmlElement(name="creditCardAccount"),
            @XmlElement(name="mortgageAccount")
    })
//    private Map<String,Debt> debts = new HashMap<>();
    private List<Debt> debtList = new ArrayList<>();

//    @XmlElement
//    private List<Debt> bankAccount;
//    @XmlElement
//    private List<Debt> creditCardAccount;
//    @XmlElement
//    private List<Debt> mortgageAccount;


}
