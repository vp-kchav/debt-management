package com.mcm.demo.pattern.processor;


import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.model.output.ConsumerOutput;
import com.mcm.demo.model.output.ConsumersOutput;
import com.mcm.demo.model.request.Consumers;
import com.mcm.demo.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author kchav
 *
 */
@Component
@Slf4j
public class XmlFileProcessor extends FileProcessor {

	@Override
	public FileType getType() {
		return FileType.XML;
	}

	/**
	 * @param file
	 */
	@Override
	public void process(File file) {
		String locationMove = "";
		try {
			log.info("XmlFileProcessor started processing file: {} ", file.getName());
			JAXBContext jc = JAXBContext.newInstance(Consumers.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			Consumers consumers = (Consumers) unmarshaller.unmarshal(document);
			if (consumers != null) {
				consumers.getConsumer().forEach(xmlConsumer -> {
					ConsumerEntity entity = getDomainObjectMapper().mapXmlConsumerToConsumerEntity(xmlConsumer);
					getConsumerPersistenceAdapter().createConsumer(entity);
				});
				locationMove = getFtpArchiveLocation();
			}
		} catch (ParserConfigurationException | SAXException | IOException | JAXBException e) {
			log.error("Error while processing xml file  : ", e.getMessage());
			locationMove = getFtpErrorLocation();
		} finally {
			//TODO : uncomment this, because for testing purpose need to comment it
			//moveFile(file,locationMove);
		}
	}

	/**
	 * @param
	 */
	@Override
	public void processOutput() {
		log.info("XmlFileProcessor started processing output file for: {} ", LocalDateTime.now().toString());
		List<ConsumerEntity> listConsumer = getConsumerPersistenceAdapter().findConsumerEntityToday();
		ConsumersOutput output = new ConsumersOutput();
		listConsumer.forEach(consumerEntity -> {
			ConsumerOutput consumerOutput = getDomainObjectMapper().map(consumerEntity, ConsumerOutput.class);
			output.getConsumer().add(consumerOutput);
		});
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ConsumersOutput.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File directory = new File(getOutputLocation().concat(DateTimeUtils.INSTANCE.getDateAsString(ApplicationConstants.DATE_FORMAT)));
			if (!directory.exists()) {
				directory.mkdir();
			}
			File fileOutput = new File(directory.getPath() + ApplicationConstants.OUTPUT_FILE_NAME);
			fileOutput.createNewFile();
			jaxbMarshaller.marshal(output, fileOutput);
			log.info(output.toString());
		} catch (JAXBException | IOException e) {
			log.error("Exception error during processing output file. {}", e.getMessage());
		}
	}

	/**
	 * @param file
	 * @param destination
	 */
	private void moveFile(final File file, final String destination) {
		try {
			log.info("Started moving file to {} after processing file {}", destination, file.getName());
			File destDir = new File(destination.concat(DateTimeUtils.INSTANCE.getDateAsString(ApplicationConstants.DATE_FORMAT)));
			FileUtils.moveToDirectory(file, destDir, true);
		} catch (IOException e) {
			log.error("File processing was completed, there was an error during moving file: {}", e.getMessage());
		}
	}
}