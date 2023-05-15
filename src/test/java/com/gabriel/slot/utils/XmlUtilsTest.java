
//Namespace
package com.gabriel.slot.utils;

//Imports

import com.gabriel.slot.domain.model.MathModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;


/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XmlUtilsTest {

    /**
     * Test conversion from xml as string into object
     * @throws Exception
     */
    @Test
    public void xmlToObjTest() throws Exception {

        //Create xml
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><mathModel id=\"416\" name=\"test Math Model\" modelRtp=\"9345\" symbols=\"S1 S2 S5\"/>";

        //Transform
        MathModel mathModel = (MathModel) XmlUtils.xmlToObj(xml,MathModel.class);

        //Verify
        Assertions.assertNotNull(mathModel);
        Assertions.assertEquals(416, mathModel.getId().intValue());
        Assertions.assertEquals("test Math Model", mathModel.getName());
        Assertions.assertEquals(9345, mathModel.getModelRtp().intValue());
        Assertions.assertEquals(3, mathModel.getSymbols().size());
    }

    /**
     * Test conversion from object to xml
     * @throws Exception
     */
    @Test
    public void objToXmlTest() throws Exception {

        MathModel mathModel = new MathModel();
        mathModel.setId(416);
        mathModel.setName("test Math Model");
        mathModel.setModelRtp(9345);
        mathModel.setSymbols(Arrays.asList("S1", "S2", "S3"));

        String xml = (String) XmlUtils.objToXml(mathModel);

        Assertions.assertTrue(xml.indexOf("test Math Model") > -1);
    }
}

