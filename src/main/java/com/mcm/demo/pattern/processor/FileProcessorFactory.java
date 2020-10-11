package com.mcm.demo.pattern.processor;

import com.mcm.demo.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;

/**
 * 
 * @author kchav
 *
 */
@Component
public class FileProcessorFactory {

	@Autowired
	private List<FileProcessor> fileProcessorBuilders;
	
	static EnumMap<FileType, FileProcessor> lookupStrategy = new EnumMap<>(FileType.class);

	@PostConstruct
	public void initOrchestratorsMap() {
		fileProcessorBuilders.forEach(builder -> lookupStrategy.put(builder.getType(), builder));
	}

	public FileProcessor getFileProcessor(FileType fileType) {
		return lookupStrategy.get(fileType);
	}
}
