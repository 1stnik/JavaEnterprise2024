package com.hillel.task_management_system.exceptions;

public class TaskExistsException extends RuntimeException{

    public TaskExistsException(String message) {
        super(message);
    }

    public TaskExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
