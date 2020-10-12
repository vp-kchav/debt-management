package com.mcm.demo.pattern.processor;

import com.mcm.demo.adapter.persistence.adapter.ConsumerPersistenceAdapter;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.mapper.DomainObjectMapper;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.utils.DateTimeUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author kchav
 */
@Getter
@Slf4j
public abstract class FileProcessor {

	@Value("${ftp.location.archive}")
	private String ftpArchiveLocation;

	@Value("${ftp.location.error}")
	private String ftpErrorLocation;

	@Value("${ftp.location.output}")
	private String outputLocation;

	@Autowired
	private DomainObjectMapper domainObjectMapper;

	@Autowired
	private ConsumerPersistenceAdapter consumerPersistenceAdapter;

	abstract public FileType getType();

	abstract public void process(File file);

	abstract public void processOutput(List<ConsumerEntity> listConsumerEntity);

	/**
	 * @param file
	 * @param destination
	 */
	public void moveFile(final File file, final String destination) {
		try {
			log.info("Started moving file to {} after processing file {}", destination, file.getName());
			File destDir = new File(destination.concat(DateTimeUtils.INSTANCE.getDateAsString(ApplicationConstants.DATE_FORMAT)));
			FileUtils.moveToDirectory(file, destDir, true);
		} catch (IOException e) {
			log.error("File processing was completed, there was an error during moving file: {}", e.getMessage());
		}
	}

}
