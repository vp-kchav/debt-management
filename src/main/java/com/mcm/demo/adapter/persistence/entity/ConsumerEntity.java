package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@ToString(callSuper=true, includeFieldNames=true)
@Table(name = "consumer")
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "candidate_seq")
//@DiscriminatorColumn(name="debt_type")
@Getter
@Setter
@Entity
public class ConsumerEntity extends BaseEntity {

    @Column(name = "consumerId", nullable = false)
    private String consumerId;

    @Column(name = "consumerName")
    private String consumerName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer", fetch = FetchType.EAGER)
    private Set<DebtEntity> debts = new HashSet<>();
}
