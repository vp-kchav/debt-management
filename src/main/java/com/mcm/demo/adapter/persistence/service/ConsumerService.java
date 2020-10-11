package com.mcm.demo.adapter.persistence.service;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;

import java.util.List;

/**
 * @author kchav
 */
public interface ConsumerService {

    /**
     *
     * @param consumerEntity
     * @return
     */
    ConsumerEntity save(ConsumerEntity consumerEntity);

    /**
     *
     * @param Id
     * @return
     */
    ConsumerEntity findConsumerById(Long Id);

    /**
     * @return
     */
    List<ConsumerEntity> findConsumerEntityToday();

    /**
     * @return
     */
    Integer countConsumerAddedByDate();
}
