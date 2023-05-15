package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Reel;
import com.gabriel.slot.domain.model.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParsingServiceTest {

    //Fields
    @Autowired
    private transient ParsingService parsingService;

    @Test
    public void objToJsonTest() {

        //Create object
        Reel reel = new Reel();
        reel.setId(1);
        reel.setSymbols(Arrays.asList(Symbol.BAR, Symbol.BELL, Symbol.CHERRY));


        String json = (String) parsingService.objToJson(reel);
        Assertions.assertTrue(json.length() > 0);
    }

    @Test
    public void jsonToObjTest() {

        //Create object
        Reel reel = new Reel();
        reel.setId(1);
        reel.setSymbols(Arrays.asList(Symbol.BAR, Symbol.BELL, Symbol.CHERRY));

        String json = (String) parsingService.objToJson(reel);
        Reel reelParsed = (Reel) parsingService.jsonToObj(json, Reel.class);

        //Verify
        Assertions.assertNotNull(reelParsed);
        Assertions.assertEquals(reel.getId(), reelParsed.getId());
        Assertions.assertEquals(reel.getSymbols().get(0), reelParsed.getSymbols().get(0));
        Assertions.assertEquals(reel.getSymbols().get(1), reelParsed.getSymbols().get(1));
        Assertions.assertEquals(reel.getSymbols().get(2), reelParsed.getSymbols().get(2));
    }
}
