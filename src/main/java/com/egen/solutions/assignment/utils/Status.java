package com.egen.solutions.assignment.utils;

/**
 * @author Rajasekhar
 *         <p>
 *         Useful utility POJO class for sending HTTP messages
 *         <p>
 *         Instead of void as return type, this class is used wherever required
 */
public class Status {

    /**
     * Http status code
     */
    private int code;

    /**
     * Http response message
     */
    private String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Status() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
