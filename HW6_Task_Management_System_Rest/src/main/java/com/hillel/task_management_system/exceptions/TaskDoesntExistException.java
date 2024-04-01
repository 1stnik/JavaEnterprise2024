package com.hillel.task_management_system.exceptions;

public class TaskDoesntExistException extends RuntimeException{

    public TaskDoesntExistException(String message) {
        super(message);
    }

    public TaskDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
