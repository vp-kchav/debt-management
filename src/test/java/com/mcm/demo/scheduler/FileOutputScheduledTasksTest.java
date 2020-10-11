package com.mcm.demo.scheduler;

import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import com.mcm.demo.pattern.processor.XmlFileProcessor;
import com.mcm.demo.scheduled.FileOutputScheduledTasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

/**
 * @author kchav
 */
@RunWith(MockitoJUnitRunner.class)
class FileOutputScheduledTasksTest {

    @InjectMocks
    @Spy
    private FileOutputScheduledTasks fileOutputScheduledTasks;

    @Mock
    private FileProcessorFactory fileProcessorFactory;

    @Mock
    private XmlFileProcessor processor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fileOutputScheduledTasks, "fileSupport","xml");
        Mockito.when(fileProcessorFactory.getFileProcessor(Mockito.any(FileType.class))).thenReturn(processor);
        fileProcessorFactory = Mockito.mock(FileProcessorFactory.class);
    }

    @Test
    void testFileOutputScheduledTasks() throws IOException {
        fileOutputScheduledTasks.processOutputFile();
    }

}
