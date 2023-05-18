package com.gabriel.slot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.slot.exception.ParseException;
import com.gabriel.slot.service.ParseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class that handle parsing operations
 */
@Service
public class ParseServiceImpl implements ParseService {


    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(ParseServiceImpl.class);

    //Fields
    private final transient ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Cast Json to an Object.
     * @param json
     * @param aClass
     * @return
     */
    @Override
    public Object jsonToObj(String json, Class aClass) {
        try {
            return objectMapper.readValue(json, aClass);
        }
        catch (IOException e) {
            LOGGER.error("IOException while converting JSON [{}] into Pojo [{}]", json, aClass);
            throw new ParseException("IOException while converting Pojo JSON [" + json + "] into Pojo [" + aClass + "]", e);
        }
    }


    /**
     * Transform an Object to JSON
     * @param obj
     * @return
     */
    @Override
    public Object objToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            LOGGER.error("JsonProcessingException while parsing Pojo [{}]", obj.getClass());
            throw new ParseException("JsonProcessingException while parsing Pojo [" + obj.getClass() + "]", e);
        }
    }
}