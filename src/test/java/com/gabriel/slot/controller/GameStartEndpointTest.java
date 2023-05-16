package com.gabriel.slot.controller;

import com.gabriel.slot.domain.dto.api.StartResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.*;

/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameStartEndpointTest {

    //Fields
    private static final String PREFIX_URL = "/api/v1/slot/";
    private static final String SUFFIX_URL = "/start";

    @Autowired
    private transient TestRestTemplate restTemplate;


    @Test
    void startValidGameIdTest() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Create a game ID
        int gameID = 1;

        //Invoke the API service
        ResponseEntity<StartResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getGameSettings());
        Assertions.assertEquals(1,response.getBody().getGameSettings().getId());
        Assertions.assertTrue( response.getBody().getGameSettings().getMathModel() > 0);
        Assertions.assertNotNull(response.getBody().getGameSettings().getConfigData());
        Assertions.assertTrue(response.getBody().getGameSettings().getConfigData().getMaxLines() > 0);
    }

    @Test
    void startNotExistingGameIdTest() {
        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Create a game ID
        int gameID = -1;

        //Invoke the API service
        ResponseEntity<StartResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void startInvalidGameIdTest() {
        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Create a game ID
        String gameID = "ImInvalid";

        //Invoke the API service
        ResponseEntity<StartResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
