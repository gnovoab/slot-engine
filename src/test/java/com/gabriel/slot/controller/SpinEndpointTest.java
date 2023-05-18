package com.gabriel.slot.controller;

import com.gabriel.slot.domain.dto.api.ApiMessageResponse;
import com.gabriel.slot.domain.dto.api.SpinRequest;
import com.gabriel.slot.domain.dto.api.SpinResponse;
import com.gabriel.slot.domain.dto.object.ReelPosition;
import com.gabriel.slot.domain.dto.object.ReelSetPositions;
import com.gabriel.slot.domain.model.Player;
import com.gabriel.slot.domain.model.Spin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpinEndpointTest {

    //Fields
    private static final String PREFIX_URL = "/api/v1/slot/";
    private static final String SUFFIX_URL = "/spin/";

    @Autowired
    private transient TestRestTemplate restTemplate;

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
            value="NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE",
            justification="I know what I'm doing")

    @Test
    void startValidGameIdTest() {

        //Create the payload
        Spin spin = new Spin();
        spin.setStake((short) 50);
        spin.setNumLines(5);

        SpinRequest payload = new SpinRequest();
        payload.setPlayer(new Player());
        payload.setSpin(spin);

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(payload, requestHeaders);

        //Create a game ID
        int gameID = 1;
        int spinType = 0;

        //Invoke the API service
        ResponseEntity<SpinResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getSpinSimulation());
        Assertions.assertNotNull(response.getBody().getSpinSimulation().getBoard());

        String[][] board = response.getBody().getSpinSimulation().getBoard();
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Assertions.assertNotNull(board[i][j]);
                Assertions.assertNotEquals("", board[i][j]);
            }
        }

        Assertions.assertNotNull(response.getBody().getSpinSimulation().getReelSetPositions());
        ReelSetPositions positions = response.getBody().getSpinSimulation().getReelSetPositions();
        for (ReelPosition position: positions.getReelsPositions()) {
            Assertions.assertNotNull(position);;
            Assertions.assertTrue(position.getPosition() >= 0);
            Assertions.assertTrue(position.getReelNumber() >= 0);
        }

        Assertions.assertNotNull(response.getBody().getSpinResult());
        Assertions.assertTrue(response.getBody().getSpinResult().getTransactionId().length() > 0);
        Assertions.assertTrue(response.getBody().getSpinResult().getTotalWin() >= 0);
        Assertions.assertTrue(response.getBody().getSpinResult().getLines().size() >= 0);
    }

    @Test
    void spinNotExistingGameAndSpinTypeTest() {

        //Create the payload
        Spin spin = new Spin();
        spin.setStake((short) 50);
        spin.setNumLines(5);

        SpinRequest payload = new SpinRequest();
        payload.setSpin(spin);
        payload.setPlayer(new Player());

        /* ************ */
        /* Invalid GAME */
        /* ************ */

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(payload, requestHeaders);

        //Create a game ID
        int gameID = -1;
        int spinType = 0;

        //Invoke the API service
        ResponseEntity<SpinResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        /* ************ */
        /* Invalid Spin */
        /* ************ */

        gameID = 1;
        spinType = -1;

        response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


        /* *************** */
        /* Invalid Spin #2 */
        /* *************** */

        gameID = 1;
        spinType = 90;

        response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        /* ****************** */
        /* Valid Game & Spin */
        /* **************** */

        gameID = 1;
        spinType = 0;
        response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    @Test
    void invalidPayload() {
        //Create the payload
        Spin spin = new Spin();
        spin.setStake((short) -50);
        spin.setNumLines(5);

        SpinRequest payload = new SpinRequest();
        payload.setSpin(spin);
        payload.setPlayer(new Player());

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(payload, requestHeaders);

        //Create a game ID
        int gameID = 1;
        int spinType = 0;

        ResponseEntity<ApiMessageResponse> response = restTemplate.exchange(PREFIX_URL+gameID+SUFFIX_URL+spinType, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Bad Request: [stake must be greater than or equal to 1 ; ]", response.getBody().getMessage());
    }
}
