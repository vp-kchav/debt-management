package com.mcm.demo.adapter.websrest.controller;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import com.mcm.demo.scheduled.FileOutputScheduledTasks;
import com.mcm.demo.scheduled.FileProcessingScheduledTasks;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author kchav
 *
 */
@RestController
public class Controller {

	@Autowired
	private FileOutputScheduledTasks fileOutputScheduledTasks;

	@Autowired
	private FileProcessingScheduledTasks fileProcessingScheduledTasks;

	@GetMapping(value ="/processFile")
	public ResponseEntity<String>  fileProcessing() {
		try {
			fileProcessingScheduledTasks.readingBatchFile();
			return ResponseEntity.status(HttpStatus.OK).body("Successfully processing file.");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error : "+ e.getMessage());
		}
	}

	@GetMapping(value ="/outputFile")
	public ResponseEntity<String>  outputProcessing()  {
		try {
			fileOutputScheduledTasks.processOutputFile();
			return ResponseEntity.status(HttpStatus.OK).body("Successfully processing output file.");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error : "+ e.getMessage());
		}
	}
}
