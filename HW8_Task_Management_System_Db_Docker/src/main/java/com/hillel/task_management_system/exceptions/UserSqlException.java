package com.hillel.task_management_system.exceptions;

import java.sql.SQLException;

public class UserSqlException extends SQLException {

    public UserSqlException(String message) {
        super(message);
    }

    public UserSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
