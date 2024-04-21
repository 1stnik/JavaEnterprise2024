package com.hillel.task_management_system.controller;

import com.hillel.task_management_system.model.User;
import com.hillel.task_management_system.service.UserService;
import com.hillel.task_management_system.service.UserServiceFactory;
import com.hillel.task_management_system.service.UserServiceJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private final UserServiceFactory userServiceFactory;
    private final UserService userService;

    @Autowired
    public UserController(UserServiceFactory userServiceFactory) {
        this.userServiceFactory = userServiceFactory;
        this.userService = userServiceFactory.createUserService();
    }


    @PostMapping(path = "/add_user")
    public String addUser(@RequestBody User user) throws SQLException {
        return userService.addUser(user);
    }

    @GetMapping("/get_user_by_id/{userId}")
    public User getUserById(@PathVariable int userId) throws SQLException {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/remove_user/{userId}")
    public String removeUser(@PathVariable int userId) throws SQLException {
        return userService.removeUser(userId);
    }

    @GetMapping("/get_all_users")
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

}
