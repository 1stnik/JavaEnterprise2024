package com.hillel.task_management_system.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    // Global Exceptions
    @ExceptionHandler(Exception.class)
    public ErrorBody handleGlobalException(Exception e) {
        e.printStackTrace();
        return new ErrorBody(e.getMessage());
    }


    // Task Exceptions
    @ExceptionHandler(TaskNullException.class)
    public ErrorBody handleTaskNullException(Exception e) {
        e.printStackTrace();
        return new ErrorBody(e.getMessage());
    }

    @ExceptionHandler(TaskSqlException.class)
    public ErrorBody handleTaskSqlException(Exception e) {
        e.printStackTrace();
        return new ErrorBody(e.getMessage());
    }


    // User Exceptions
    @ExceptionHandler(UserNullException.class)
    public ErrorBody handleUserNullException(Exception e) {
        e.printStackTrace();
        return new ErrorBody(e.getMessage());
    }

    @ExceptionHandler(UserSqlException.class)
    public ErrorBody handleUserSqlException(Exception e) {
        e.printStackTrace();
        return new ErrorBody(e.getMessage());
    }


    static class ErrorBody {

        private final String errorDescription;

        public ErrorBody(String errorDescription) {
            this.errorDescription = errorDescription;
        }

        public String getErrorDescription() {
            return errorDescription;
        }
    }

}
