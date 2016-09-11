package com.egen.solutions.assignment.utils;

/**
 * @author Rajasekhar
 *         <p>
 *         Useful utility POJO class for sending HTTP messages
 *         <p>
 *         Instead of void as return type, this class is used wherever required
 */
@SuppressWarnings("unused")
public class Status {

    /**
     * Http status code
     */
    private String code;

    /**
     * Http response message
     */
    private String message;

    public Status(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Status() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
