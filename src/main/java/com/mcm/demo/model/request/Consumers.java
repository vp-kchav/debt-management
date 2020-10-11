package com.mcm.demo.model.request;

import com.mcm.demo.model.xml.XmlConsumer;
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
    private List<XmlConsumer> consumer = new ArrayList<>();
}
