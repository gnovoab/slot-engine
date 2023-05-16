
//Namespace
package com.gabriel.slot.utils;

//Imports

import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.domain.model.game.SlotGameCatalog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;


/**
 * Unit test class
 */
@SuppressWarnings("checkstyle:lineLength")
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
     * Test conversion from xml as string into object
     * @throws Exception
     */
    @Test
    public void xmlToObjTest2() throws Exception {

        //Create xml
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <slotGameCatalog name=\"list of game\"><game id=\"1\" name=\"Simple 3 reel fruit slot\" mathModel=\"1\" progressiveRtp=\"0\"><configData maxBet=\"10\" maxWin=\"10\" maxLines=\"1\"><stakes>1 2 3 5 7 10 15 25 30 40 50 100</stakes><autoplay>10 25 50 100</autoplay></configData></game><game id=\"2\" name=\"Simple 3 reel fruit slot with Multiple paylines\" mathModel=\"1\" progressiveRtp=\"0\"></game></slotGameCatalog>";

        //Transform
        SlotGameCatalog slotGameCatalog = (SlotGameCatalog) XmlUtils.xmlToObj(xml, SlotGameCatalog.class);

        //Verify
        Assertions.assertNotNull(slotGameCatalog);
        Assertions.assertEquals(1, slotGameCatalog.getGames().get(0).getId());
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

