package com.mcm.demo.model.xml;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kchav
 */
@XmlRootElement(name = "consumers")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class Consumers {

    @XmlElement
    public List<XmlConsumer> consumer = new ArrayList<>();
}
