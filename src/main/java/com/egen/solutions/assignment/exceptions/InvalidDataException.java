package com.egen.solutions.assignment.exceptions;

/**
 * @author Rajasekhar
 */
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
