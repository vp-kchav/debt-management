package com.mcm.demo.scheduled;

import com.mcm.demo.adapter.persistence.adapter.ConsumerPersistenceAdapter;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class FileOutputScheduledTasks {

    @Value("${file.support}")
    private String fileSupport;

    @Autowired
    private FileProcessorFactory fileProcessorFactory;

    @Autowired
    private ConsumerPersistenceAdapter consumerPersistenceAdapter;

    @Scheduled(cron = "${scheduled.cron.output}")
    public void processOutputFile() throws IOException {
        log.info("Scheduler started output file");
        try {
            List<ConsumerEntity> listConsumer = consumerPersistenceAdapter.findConsumerEntityToday();
            List<String> supportedFiles = Arrays.asList(fileSupport.split(ApplicationConstants.FILE_SUPPORT_SEPARATION));
            supportedFiles.forEach(supportedFile ->{
                FileType fileType = ApplicationConstants.FILE_TYPE_PROCESSOR_MAP.get(supportedFile);
                if(fileType!=null) {
                   fileProcessorFactory.getFileProcessor(fileType).processOutput(listConsumer);
                } else  {
                    log.info("file with extension '{}' is not supported.", supportedFile);
                }
            });
        } catch (Exception e) {
            log.error("There was an error during retrieve data from DB.");
        }
        log.info("Scheduler output file has completed");
    }

}
