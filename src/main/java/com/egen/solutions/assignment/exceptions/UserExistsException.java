package com.egen.solutions.assignment.exceptions;

/**
 * @author Rajasekhar
 */
public class UserExistsException extends RuntimeException {
    public UserExistsException(String message) {
        super(message);
    }
}
