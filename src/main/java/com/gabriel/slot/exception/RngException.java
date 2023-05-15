package com.gabriel.slot.exception;

/**
 * Exception related to Random number generation
 */
public class RngException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public RngException(String message, Throwable cause) {
        super(message, cause);
    }
}

