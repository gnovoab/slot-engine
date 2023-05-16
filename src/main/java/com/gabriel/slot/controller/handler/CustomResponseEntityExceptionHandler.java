package com.gabriel.slot.controller.handler;

import com.gabriel.slot.domain.dto.api.ApiErrorResponse;
import com.gabriel.slot.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Class that handles Custom Exceptions from the app
 */
@SuppressWarnings("PMD.CommentRequired")
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(BoardException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(FileException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ParseException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RngException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(RngException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(XmlParseException.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(XmlParseException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
