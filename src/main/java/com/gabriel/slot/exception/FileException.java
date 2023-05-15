package com.gabriel.slot.exception;

import java.io.Serial;

/**
 * Exception related processing files.
 */
public class FileException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
