package com.gabriel.slot;

import com.fasterxml.jackson.databind.JsonNode;
import com.gabriel.slot.controller.SlotController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

/**
 * Basic Intergration tests
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SlotApplicationTests {

	@Autowired
	private transient TestRestTemplate restTemplate;

	@Autowired
	private SlotController controller;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

	@Test
	public void healthOk() {
		ResponseEntity<JsonNode> entity = restTemplate.getForEntity("/actuator/health", JsonNode.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}


	@Test
	public void swaggerOK() {
		ResponseEntity<String> entity = restTemplate.getForEntity("/swagger-ui.html", String.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	@Test
	public void apidocsJson() {
		ResponseEntity<String> entity = restTemplate.getForEntity("/v3/api-docs", String.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void apidocsYaml() {
		ResponseEntity<String> entity = restTemplate.getForEntity("/v3/api-docs.yaml", String.class);
		Assertions.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}



}
