package com.gabriel.slot.exception;

import java.io.Serial;

/**
 * Exception related where a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
