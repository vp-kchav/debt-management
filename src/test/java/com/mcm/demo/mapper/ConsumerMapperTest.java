package com.mcm.demo.mapper;

import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.mapper.DomainObjectMapper;
import com.mcm.demo.model.request.Consumer;
import com.mcm.demo.util.DataUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kchav
 */
@SpringBootTest(classes = {DomainObjectMapper.class})
@RunWith(SpringRunner.class)
public class ConsumerMapperTest {

    @Autowired
    private DomainObjectMapper domainObjectMapper;


    @BeforeEach
    public void setup() {

    }

    @Test
    public void testMappingFromConsumerToConsumerEntity() {
        Consumer consumer = DataUtils.getConsumer();
        ConsumerEntity entity = domainObjectMapper.mapConsumerToConsumerEntity(consumer);
        Assert.assertNotNull(entity);
    }

    @Test
    public void testMappingFromConsumerXmlToConsumerEntity() {

    }
}
