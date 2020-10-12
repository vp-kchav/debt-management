package com.mcm.demo.processor;

import com.mcm.demo.adapter.persistence.adapter.ConsumerPersistenceAdapter;
import com.mcm.demo.adapter.persistence.mapper.DomainObjectMapper;
import com.mcm.demo.pattern.processor.XmlFileProcessor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author kchav
 */
@RunWith(MockitoJUnitRunner.class)
public class XmlFileProcessorTest {

    @InjectMocks
    @Spy
    private XmlFileProcessor xmlFileProcessor;

    @Mock
    private DomainObjectMapper domainObjectMapper;

    @Mock
    private ConsumerPersistenceAdapter consumerPersistenceAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        ReflectionTestUtils.setField(fileProcessingScheduledTasks, "ftpFolderLocation","/Users/kchav/Documents/OneDrive/Job/mcm/ftp/files/");
//        Mockito.when(fileProcessorFactory.getFileProcessor(Mockito.any(FileType.class))).thenReturn(processor);
//        fileProcessorFactory = Mockito.mock(FileProcessorFactory.class);
    }

    @Test
    public void testProcess() throws IOException {
        File file = new File(getClass().getClassLoader().getResource("consumer.xml").getFile());
        xmlFileProcessor.process(file);
        //fileProcessingScheduledTasks.readingBatchFile();
    }

}
