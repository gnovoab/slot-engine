package com.gabriel.slot.exception;

import java.io.Serial;

/**
 * Exception related where a resource is not found.
 */
public class XmlParseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
