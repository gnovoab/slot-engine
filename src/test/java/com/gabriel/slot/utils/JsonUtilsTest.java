
//Namespace
package com.gabriel.slot.utils;

//Imports

import com.gabriel.slot.domain.model.mathmodel.MathModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test class
 */


@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonUtilsTest {

    /**
     * Test conversion from xml as string into object
     * @throws Exception
     */
    @Test
    public void objToJsonTest() throws Exception {

        MathModel mathModel = new MathModel();
        mathModel.setId(416);
        mathModel.setName("test Math Model");
        mathModel.setModelRtp(9345);

        String json = (String) JsonUtils.objToJson(mathModel);

        Assertions.assertTrue(json.indexOf("test Math Model") > -1);

    }

    /**
     * Test conversion from xml as string into object
     * @throws Exception
     */
    @Test
    public void jsonToObjTest() throws Exception {
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
