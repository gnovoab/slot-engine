package com.gabriel.slot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RngServiceTest {

    //Fields
    @Autowired
    private transient RngService rngService;

    @Test
    void retrieveRandomNumberTest() {

        for(int i=1; i<20; i++){
            int number = rngService.retrieveRandomNumber(i);
            Assertions.assertTrue(number >= 0 && number < i);
        }

    }
}