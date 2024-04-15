package com.hillel.task_management_system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ConnectionConfig {

    @Value("jdbc:postgresql://localhost:5432/task_manager_db")
    private String url;

    @Value("postgres")
    private String username;

    @Value("08080808")
    private String password;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
        return getConnection().prepareStatement(sqlQuery);
    }

}
