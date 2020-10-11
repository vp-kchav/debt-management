package com.mcm.demo.adapter.persistence.service;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    private ConsumerRepository consumerRepository;


    @Override
    public ConsumerEntity save(ConsumerEntity consumerEntity) {
        return consumerRepository.save(consumerEntity);
    }

    @Override
    public ConsumerEntity findConsumerById(Long id) {
        return consumerRepository.findById(id).get();
    }

    @Override
    public List<ConsumerEntity> findConsumerEntityToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return consumerRepository.findConsumerEntityByCreatedTimestampBetween(yesterday, now);
    }

    @Override
    public Integer countConsumerAddedByDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        return consumerRepository.countConsumerEntitiesByCreatedTimestampBetween(yesterday, now);
    }
}
