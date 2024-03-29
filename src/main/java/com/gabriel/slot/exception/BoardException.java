package com.gabriel.slot.exception;

import java.io.Serial;

/**
 * Exception related transposing reels.
 */
public class BoardException extends RuntimeException {

    @Serial
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
