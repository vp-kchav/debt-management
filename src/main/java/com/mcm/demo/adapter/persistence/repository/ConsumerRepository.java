package com.mcm.demo.adapter.persistence.repository;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumerRepository extends CrudRepository<ConsumerEntity,Long> {

    List<ConsumerEntity> findConsumerEntityByLastUpdatedTimestampBetween(LocalDateTime startDateTime,LocalDateTime endDateTime);

    Integer countConsumerEntitiesByLastUpdatedTimestampBetween(LocalDateTime startDateTime,LocalDateTime endDateTime);

    ConsumerEntity findConsumerEntityByConsumerId(String consumerId);
}
