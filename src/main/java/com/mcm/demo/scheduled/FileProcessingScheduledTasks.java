package com.mcm.demo.scheduled;

import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FileProcessingScheduledTasks {

    @Value("${ftp.location.folder}")
    private String ftpFolderLocation;

    @Autowired
    private FileProcessorFactory fileProcessorFactory;

    @Scheduled(cron = "${scheduled.cron.read}")
    public void readingBatchFile() throws IOException {
        log.info("Scheduler started for processing files");
        processRecordFromFtpLocation(ftpFolderLocation);
    }
    /**
     *
     * @param folderLocation
     * @return
     * @throws IOException
     */
    private void processRecordFromFtpLocation(final String folderLocation) throws IOException {
        log.info("process RecordFromFtpLocation in {}" , folderLocation);
        final File folder = new File(folderLocation);
        List<File> files = listFilesForFolder(folder);
        files.forEach(file -> {
            String fileExtension = FilenameUtils.getExtension(file.getName());
            FileType fileType = ApplicationConstants.FILE_TYPE_PROCESSOR_MAP.get(fileExtension);
            if(fileType!=null) {
                fileProcessorFactory.getFileProcessor(fileType).process(file);
            } else  {
                log.info("file with extension '{}' is not supported.", fileExtension);
            }
        });
        log.info("completed process RecordFromFtpLocation.");
    }

    /**
     *
     * @param folder
     */
    public List<File> listFilesForFolder(final File folder) {
        log.info("started listing all the file from folder {}",folder.getName());
        List<File> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                files.add(fileEntry);
            }
        }
        return files;
    }
}
