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
    public void createConsumer(final ConsumerEntity consumerEntity) {
        try {
            ConsumerEntity existingConsumer = consumerService.findByConsumerId(consumerEntity.getConsumerId());
            boolean updateCreationDate = true;
            if(existingConsumer != null) {
                //existingConsumer.getDebts().addAll(consumerEntity.getDebts());
                consumerEntity.setId(existingConsumer.getId());
                updateCreationDate = false;
                log.info("consumerId is already exist in the DB : {} ", existingConsumer);
            }
            populateAuditColumn(consumerEntity,updateCreationDate);
            consumerService.save(consumerEntity);
            log.info("Consumer entity after added : {} ", consumerEntity);
        } catch (Exception e) {
            log.error("There was an error while processing data to Database");
        }
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

    private void populateAuditColumn(final ConsumerEntity consumerEntity, final boolean updateCreationDate){
        if(updateCreationDate) {
            consumerEntity.setCreatedTimestamp(LocalDateTime.now());
        }
        consumerEntity.setLastUpdatedTimestamp(LocalDateTime.now());
        //TODO: update who did this for createdBy
    }
}
