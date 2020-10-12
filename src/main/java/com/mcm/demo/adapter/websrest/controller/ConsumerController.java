package com.mcm.demo.adapter.websrest.controller;//package com.mcm.demo.controller;

import com.mcm.demo.adapter.persistence.adapter.ConsumerPersistenceAdapter;
import com.mcm.demo.adapter.persistence.entity.ConsumerEntity;
import com.mcm.demo.adapter.persistence.mapper.DomainObjectMapper;
import com.mcm.demo.adapter.persistence.repository.ConsumerRepository;
import com.mcm.demo.model.request.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kchav
 *
 */
@RestController
@Slf4j
public class ConsumerController {

	@Autowired
	private ConsumerPersistenceAdapter consumerPersistenceAdapter;

	@Autowired
	ConsumerRepository repo;

	@Autowired
	DomainObjectMapper domainObjectMapper;

	@Value("${max.message.perDay}")
	private int maxMessagePerDay;

	@Value("${max.account}")
	private int maxAccountPerDay;
	/**
	 *
	 * @param consumerBody
	 * @return
	 */
	@PostMapping(value ="${consumer.add}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> addConsumer(@RequestHeader("client-context") String clientContext,@RequestBody Consumer consumerBody) {
		try {
			int countMessageAdded = consumerPersistenceAdapter.countConsumerEntityToday();
			if(countMessageAdded >= maxMessagePerDay) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sorry, we have reached which is max message per day, which is " + maxMessagePerDay);
			} else if(consumerBody.getDebts().size() > maxAccountPerDay) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account should not be more than " + maxAccountPerDay );
			} else {
				log.info("Received request: {}", consumerBody.toString());
				ConsumerEntity entity = domainObjectMapper.mapConsumerToConsumerEntity(consumerBody);
				consumerPersistenceAdapter.createConsumer(entity);
				log.info("Entity saved: {} ", entity.toString());
				return ResponseEntity.status(HttpStatus.OK).body("Successfully Added message.");
			}
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}

}
