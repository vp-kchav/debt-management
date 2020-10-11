package com.mcm.demo.adapter.persistence.repository;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumerRepository extends CrudRepository<ConsumerEntity,Long> {

    List<ConsumerEntity> findConsumerEntityByCreatedTimestampBetween(LocalDateTime startDateTime,LocalDateTime endDateTime);

    Integer countConsumerEntitiesByCreatedTimestampBetween(LocalDateTime startDateTime,LocalDateTime endDateTime);
}
