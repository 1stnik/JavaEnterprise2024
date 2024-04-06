package com.hillel.task_management_system.exceptions;

public class UserDoesntExistException extends RuntimeException{

    public UserDoesntExistException(String message) {
        super(message);
    }

    public UserDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
