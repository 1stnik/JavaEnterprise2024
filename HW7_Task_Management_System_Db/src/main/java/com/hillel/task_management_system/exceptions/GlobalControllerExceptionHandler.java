package com.hillel.task_management_system.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorBody handleGlobalException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("Global Error Description");
    }

    @ExceptionHandler(TaskNullException.class)
    public ErrorBody handleTaskNullException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("Task value is NULL");
    }

    @ExceptionHandler(TaskDoesntExistException.class)
    public ErrorBody handleTaskDoesntExistsException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("Task doesn't exist");
    }






    @ExceptionHandler(UserDoesntExistException.class)
    public ErrorBody handleUserDoesntExistsException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("User doesn't exist in the DB");
    }

    @ExceptionHandler(UserExistsException.class)
    public ErrorBody handleUserExistsException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("User with that ID has already existed in the DB");
    }

    @ExceptionHandler(UserNullException.class)
    public ErrorBody handleUserNullException(Exception e) {
//        System.out.println("We have a problem! " + e.getMessage());
        e.printStackTrace();
        return new ErrorBody("User value is NULL");
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
