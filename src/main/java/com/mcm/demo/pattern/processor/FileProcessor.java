package com.mcm.demo.pattern.processor;

import com.mcm.demo.adapter.persistence.adapter.ConsumerPersistenceAdapter;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.mapper.DomainObjectMapper;
import com.mcm.demo.model.FileType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;

/**
 * 
 * @author kchav
 */
@Getter
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

}
