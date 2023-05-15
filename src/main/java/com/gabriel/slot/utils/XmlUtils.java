
//Namespace
package com.gabriel.slot.utils;

//Imports
import com.gabriel.slot.exception.XmlParseException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Utils class for xml parsing
 */
@SuppressWarnings("PMD")
public final class XmlUtils {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Constructor
     */
    private XmlUtils(){}


    /**
     * Transofrm XML file into a Object
     * @param path
     * @param fileName
     * @return
     */
    public static Object xmlFileToObj(String path, String fileName, Class className) {
        try {
            LOGGER.info("Converting XML file [{}] into an Object ", fileName);

            File file = new File(path + "/" + fileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(className);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Object obj = jaxbUnmarshaller.unmarshal(file);

            LOGGER.info("Returning object from file [{}] ", fileName);
            return obj;
        }
        catch (JAXBException e) {
            LOGGER.error("JAXBException while converting file [{}] into an Object ", fileName, e);
            throw new XmlParseException("JAXBException while converting file [" + fileName + "] into an Object", e);
        }
    }



    /**
     * Transform xml into obj
     * @param xmlInString
     * @param className
     * @return
     */
    public static Object xmlToObj(String xmlInString, Class className) {

        try {

            LOGGER.info("Converting XML [{}] into an Object ", xmlInString);

            JAXBContext jaxbContext = JAXBContext.newInstance(className);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlInString);
            Object obj = unmarshaller.unmarshal(reader);

            LOGGER.info("Returning object from xml [{}] ", xmlInString);

            return obj;
        }
        catch (JAXBException e) {
            LOGGER.error("JAXBException while converting xml [{}] into an Object ", xmlInString, e);
            throw new XmlParseException("JAXBException while converting xml [" + xmlInString + "] into an Object", e);
        }

    }


    /**
     * This method is used to convert the POJO object to XML String using JAXB Utility
     * @param obj
     * @return
     */
    public static Object objToXml(Object obj){

        try {
            LOGGER.info("Converting Object [{}] into an XML ", obj);
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(obj, sw);

            LOGGER.info("Returning object [{}] as XML", obj);
            return sw.toString();
        }
        catch (JAXBException e) {
            LOGGER.error("JAXBException while converting object [{}] into an XML ", obj, e);
            throw new XmlParseException("JAXBException while converting object [" + obj + "] into an XML", e);
        }
    }
}
