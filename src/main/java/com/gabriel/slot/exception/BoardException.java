package com.gabriel.slot.exception;

/**
 * Exception related processing files.
 */
public class BoardException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }
}
