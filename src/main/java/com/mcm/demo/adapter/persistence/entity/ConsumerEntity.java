package com.mcm.demo.adapter.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@ToString(callSuper=true, includeFieldNames=true)
@Table(name = "consumer")
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "candidate_seq")
@DiscriminatorColumn(name="Debt_Type")
@Getter
@Setter
@Entity
public class ConsumerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "id")
    private Long id;

    @Column(name = "consumerId", nullable = false)
    private String consumerId;

    @Column(name = "consumerName", nullable = false)
    private String consumerName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer", fetch = FetchType.EAGER)
    private Set<DebtEntity> debts = new HashSet<>();
}
