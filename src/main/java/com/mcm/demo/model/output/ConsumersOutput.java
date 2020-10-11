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
@XmlRootElement(name = "consumers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumersOutput extends BaseEntity {

    @XmlElement
    private List<ConsumerOutput> consumer = new ArrayList<>();
}
