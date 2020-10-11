package com.mcm.demo.adapter.websrest.controller;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.pattern.processor.FileProcessorFactory;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${ftp.location.folder}")
	private String ftpFolderLocation;

	@Autowired
	private FileProcessorFactory fileProcessorFactory;

	@GetMapping(value ="${path.helloworld}")
	public String index() throws IOException, JSchException {
//			ChannelSftp channelSftp = setupJsch();
//			channelSftp.connect();

		final File folder = new File(ftpFolderLocation);
		List<File> files = listFilesForFolder(folder);
		files.forEach(file -> {
			String fileExtension = FilenameUtils.getExtension(file.getName());
			FileType fileType = ApplicationConstants.FILE_TYPE_PROCESSOR_MAP.get(fileExtension);
			if(fileType!=null) {
				fileProcessorFactory.getFileProcessor(fileType).process(file);
			} else  {
				//log.info("file with extension '{}' is not supported.", fileExtension);
			}
		});

	    return " hello from me";
	}

	/**
	 *
	 * @param folder
	 */
	public List<File> listFilesForFolder(final File folder) {
		//log.info("started listing all the file from folder {}",folder.getName());
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

	private ChannelSftp setupJsch() {
		try {
			JSch jsch = new JSch();
			Session jschSession = jsch.getSession("kchav", "localhost",22);
			jschSession.setPassword("Intuit*986008");
			jschSession.connect();
			ChannelSftp channel =  (ChannelSftp) jschSession.openChannel("sftp");
			return channel;
		}catch (
			JSchException e) {
		}
		return null;
	}
}
