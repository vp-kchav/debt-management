/**
 * Created January 26,2019
 */
package com.mcm.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kimtey
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testAddConsumer() {
        //HttpEntity<String> entity = new HttpEntity<String>(bodyPayload, headers);
        //ResponseEntity<String> response = restTemplate.exchange("/consumer",HttpMethod.POST, entity, String.class);
        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
