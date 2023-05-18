package com.gabriel.slot.service.impl;


import com.gabriel.slot.service.ParseService;
import com.gabriel.slot.service.ValidatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Class that handles operations regarding Random Number Generation
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    //Fields
    @Autowired
    private transient ParseService parsingService;

    @Autowired
    private transient Validator validator;

    /**
     * Validate an object given
     * @param object
     */
    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> violation : violations) {
                sb.append(violation.getPropertyPath().toString()).append(' ').append(violation.getMessage());
                sb.append(" ; ");
            }
            LOGGER.error("ValidationException while validating pojo[{}] [{}] [{}]", object.getClass().getName(), parsingService.objToJson(object),  sb.toString());
            throw new ValidationException("Bad Request: [" + sb.toString() + "]");

        }
    }
}

