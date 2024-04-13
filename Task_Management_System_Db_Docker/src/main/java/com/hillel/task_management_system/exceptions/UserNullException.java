package com.hillel.task_management_system.exceptions;

public class UserNullException extends RuntimeException {

    public UserNullException(String message) {
        super(message);
    }

    public UserNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
