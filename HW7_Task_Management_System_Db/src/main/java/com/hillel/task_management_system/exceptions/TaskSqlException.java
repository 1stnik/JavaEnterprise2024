package com.hillel.task_management_system.exceptions;

import java.sql.SQLException;

public class TaskSqlException extends SQLException {

    public TaskSqlException(String message) {
        super(message);
    }

    public TaskSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}
