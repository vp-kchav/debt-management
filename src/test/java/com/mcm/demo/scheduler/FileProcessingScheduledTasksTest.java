package com.mcm.demo.scheduler;

import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import com.mcm.demo.pattern.processor.XmlFileProcessor;
import com.mcm.demo.scheduled.FileProcessingScheduledTasks;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

/**
 * @author kchav
 */
@RunWith(MockitoJUnitRunner.class)
public class FileProcessingScheduledTasksTest {

    @InjectMocks
    @Spy
    private FileProcessingScheduledTasks fileProcessingScheduledTasks;

    @Mock
    private FileProcessorFactory fileProcessorFactory;

    @Mock
    private XmlFileProcessor processor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(fileProcessingScheduledTasks, "ftpFolderLocation","/Users/kchav/Documents/OneDrive/Job/mcm/ftp/files/");
        Mockito.when(fileProcessorFactory.getFileProcessor(Mockito.any(FileType.class))).thenReturn(processor);
        fileProcessorFactory = Mockito.mock(FileProcessorFactory.class);
    }

    @Test
    public void testFileOutputScheduledTasks() throws IOException {
        fileProcessingScheduledTasks.readingBatchFile();
    }

}
