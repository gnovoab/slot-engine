package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.game.SlotGame;
import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParseServiceTest {

    //Fields
    @Autowired
    private transient ParseService parseService;

    @Autowired
    private transient Map<Integer, SlotGame> gamesCatalog;

    @Test
    public void objToJsonTest() {

        MathModel mathModel = new MathModel();
        mathModel.setId(416);
        mathModel.setName("test Math Model");
        mathModel.setModelRtp(9345);

        String json = (String) JsonUtils.objToJson(mathModel);

        Assertions.assertTrue(json.indexOf("test Math Model") > -1);
    }

    @Test
    public void jsonToObjTest() {

        //Create json
        String json = "{\"id\":416,\"name\":\"test Math Model\",\"modelRtp\":9345}";

        //Transform
        MathModel mathModel = (MathModel) JsonUtils.jsonToObj(json,MathModel.class);

        //Verify
        Assertions.assertNotNull(mathModel);
        Assertions.assertEquals(416, mathModel.getId().intValue());
        Assertions.assertEquals("test Math Model", mathModel.getName());
        Assertions.assertEquals(9345, mathModel.getModelRtp().intValue());
    }
}