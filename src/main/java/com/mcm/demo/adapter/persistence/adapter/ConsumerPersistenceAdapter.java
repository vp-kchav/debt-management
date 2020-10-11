package com.mcm.demo.adapter.persistence.adapter;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.service.ConsumerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class ConsumerPersistenceAdapter {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Transactional
    public Boolean createConsumer(final ConsumerEntity consumerEntity) {
        populateAuditColumn(consumerEntity);
        ConsumerEntity savedConsumerEntity  = consumerService.save(consumerEntity);
        log.info("Consumer entity after mapping : {} ", consumerEntity);
        return null;
    }

    @Transactional
    public ConsumerEntity getConsumer() {
        return consumerService.findConsumerById(1l);
    }

    @Transactional
    public List<ConsumerEntity> findConsumerEntityToday() {
        return consumerService.findConsumerEntityToday();
    }

    @Transactional
    public Integer countConsumerEntityToday() {
        return consumerService.countConsumerAddedByDate();
    }

    private void populateAuditColumn(final ConsumerEntity consumerEntity){
        consumerEntity.setCreatedTimestamp(LocalDateTime.now());
        consumerEntity.setLastUpdatedTimestamp(LocalDateTime.now());
        //TODO: update who did this for createdBy
    }
}
