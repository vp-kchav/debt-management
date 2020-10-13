package com.mcm.demo.pattern.processor;


import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.model.ApplicationConstants;
import com.mcm.demo.model.FileType;
import com.mcm.demo.model.output.ConsumerOutput;
import com.mcm.demo.model.output.ConsumersOutput;
import com.mcm.demo.model.xml.Consumers;
import com.mcm.demo.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.atomic.AtomicInteger;

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
		AtomicInteger numberOfRecordError = new AtomicInteger();
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
					try {
						ConsumerEntity entity = getDomainObjectMapper().mapXmlConsumerToConsumerEntity(xmlConsumer);
						getConsumerPersistenceAdapter().createConsumer(entity);
						log.error("comsumerID: {} on file {} had been added successfully", xmlConsumer.getConsumerId(), file.getName());
					} catch (Exception e) {
						log.error("Error happened when processing comsumerID: {} on file {} ", xmlConsumer.getConsumerId(), file.getName());
						numberOfRecordError.getAndIncrement();
					}
				});
				if(numberOfRecordError.get() == consumers.getConsumer().size()) {
					locationMove = getFtpErrorLocation();
				} else {
					locationMove = getFtpArchiveLocation();
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException | JAXBException e) {
			log.error("Error while processing xml file  : ", e.getMessage());
			locationMove = getFtpErrorLocation();
		} finally {
			moveFile(file,locationMove);
		}
	}

	/**
	 * @param
	 */
	@Override
	public void processOutput(final List<ConsumerEntity> listConsumer) {
		log.info("XmlFileProcessor started processing output file for: {} ", LocalDateTime.now().toString());
		ConsumersOutput output = new ConsumersOutput();
		try {
			listConsumer.forEach(consumerEntity -> {
				ConsumerOutput consumerOutput = getDomainObjectMapper().map(consumerEntity, ConsumerOutput.class);
				output.getConsumer().add(consumerOutput);
			});
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
		} catch (Exception e) {
			log.error("Exception error during mapping Entity to  output Object. {}", e.getMessage());
		}
	}
}