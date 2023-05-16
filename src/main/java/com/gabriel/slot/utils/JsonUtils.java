
//Namespace
package com.gabriel.slot.utils;

//Imports

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;


/**
 * Json util class
 */
@SuppressWarnings("PMD")
public final class JsonUtils {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);


    /**
     * Constructor
     */
    private JsonUtils() {}

    /**
     * Convert an object into JSON
     * @param obj
     * @return
     */
    public static Object objToJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            LOGGER.error("Error while parsing object into json");
            return "";
        }
    }


    /**
     * From a json string to obj
     * @param jsonInString
     * @param className
     * @return
     */
    public static Object jsonToObj(String jsonInString, Class className) {
        try {

            ObjectMapper mapper = new ObjectMapper();


            Object obj = mapper.readValue(jsonInString, className);

            return obj;
        }
        catch (IOException e) {
            LOGGER.error("Error while parsing object into json");
            return null;
        }
    }



    /**
     * From a json file to Obj
     * @param filePath
     * @param fileName
     * @param className
     * @return
     */
    public static Object jsonFileToObj(String filePath, String fileName, Class className) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Object obj = mapper.readValue(new File(filePath + fileName), className);

            return obj;
        }
        catch (IOException e) {
            LOGGER.error("Error while parsing object into json");
            return null;
        }
    }


}
