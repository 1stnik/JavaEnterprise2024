package com.hillel.task_management_system.exceptions;

public class TaskNullException extends RuntimeException {

    public TaskNullException(String message) {
        super(message);
    }

    public TaskNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
