package com.mcm.demo.adapter.persistence.mapper;

import com.mcm.demo.adapter.persistence.entity.BankAccountEntity;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.entity.CreditCardAccountEntity;
import com.mcm.demo.adapter.persistence.entity.MortgageAccountEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.output.BankAccountOutput;
import com.mcm.demo.model.output.ConsumerOutput;
import com.mcm.demo.model.output.CreditCardAccountOutput;
import com.mcm.demo.model.output.MortgageAccountOutput;
import com.mcm.demo.model.request.Consumer;
import com.mcm.demo.model.request.Debt;
import com.mcm.demo.model.xml.XmlConsumer;
import com.mcm.demo.pattern.builder.ConsumerEntityHelper;
import com.mcm.demo.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Component
@Slf4j
public class DomainObjectMapper extends ConfigurableMapper {

    @Value("${thread.polling}")
    private int threadPolling;

    @Override
    protected void configure(MapperFactory mapperFactory) {
        super.configure(mapperFactory);
        mapperFactory.classMap(Consumer.class, ConsumerEntity.class)
                .exclude(ApplicationConstants.DEBT_FIELD)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(XmlConsumer.class, ConsumerEntity.class)
                .exclude(ApplicationConstants.DEBT_FIELD)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(ConsumerOutput.class,ConsumerEntity.class)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(BankAccountOutput.class,BankAccountEntity.class)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(MortgageAccountOutput.class,MortgageAccountEntity.class)
                .mapNulls(true)
                .byDefault()
                .register();

        mapperFactory.classMap(CreditCardAccountOutput.class,CreditCardAccountEntity.class)
                .mapNulls(true)
                .exclude("expirationDate")
                .customize(new CustomMapper<CreditCardAccountOutput, CreditCardAccountEntity>() {
                    @Override
                    public void mapBtoA(CreditCardAccountEntity creditCardAccountEntity, CreditCardAccountOutput creditCardAccountOutput, MappingContext context) {
                        super.mapBtoA(creditCardAccountEntity, creditCardAccountOutput, context);
                        creditCardAccountOutput.setExpirationDate(DateTimeUtils.INSTANCE.getStringDateFromDate(
                                creditCardAccountEntity.getExpirationDate(),ApplicationConstants.DISPLAY_DATE_FORMAT));
                    }
                })
                .byDefault()
                .register();
    }

    /**
     * custom mapping
     * @param consumer
     * @return
     */
    public ConsumerEntity mapConsumerToConsumerEntity(Consumer consumer){
        if(consumer != null){
            log.info("Started mapping to ConsumerEntity by consumer: {}" , consumer.toString());
            ConsumerEntity consumerEntity = this.map(consumer,ConsumerEntity.class);
            processMappingDebts(consumerEntity,consumer.getDebts());
            return consumerEntity;
        }
        return null;
    }

    /**
     * custom mapping
     * @param xmlConsumer
     * @return
     */
    public ConsumerEntity mapXmlConsumerToConsumerEntity(XmlConsumer xmlConsumer){
        if(xmlConsumer != null){
            log.info("Started mapping to ConsumerEntity by cmlConsumer: {}" , xmlConsumer.toString());
            ConsumerEntity entity = this.map(xmlConsumer,ConsumerEntity.class);
            processMappingDebts(entity,xmlConsumer.getDebts().getDebtList());
            return entity;
        }
        return null;
    }

    /**
     *
     * @param consumerEntity
     * @param debts
     */
    private void processMappingDebts(ConsumerEntity consumerEntity,List<Debt> debts) {
        log.info("Starting processing map debts with thread polling {}",threadPolling);
        ForkJoinPool accountThreadPool = new ForkJoinPool(threadPolling);
        accountThreadPool.submit(() -> {
            debts.parallelStream()
                    .forEach(debt -> {
                        ConsumerEntityHelper.INSTANCE.addDebtEntity(consumerEntity,debt);
                    });
        });
        log.info("completed processing map debts with thread polling");
    }
}
