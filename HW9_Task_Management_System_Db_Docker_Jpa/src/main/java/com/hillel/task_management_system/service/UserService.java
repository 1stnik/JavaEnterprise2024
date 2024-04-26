package com.hillel.task_management_system.service;

import com.hillel.task_management_system.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    String addUser(User user) throws SQLException;

    User getUserById(int userId) throws SQLException;

    String removeUser(int userId) throws SQLException;

    List<User> getAllUsers() throws SQLException;
}
