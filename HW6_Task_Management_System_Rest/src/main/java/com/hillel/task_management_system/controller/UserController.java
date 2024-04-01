package com.hillel.task_management_system.controller;

import com.hillel.task_management_system.model.User;
import com.hillel.task_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_user_by_id/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }


    @PostMapping(path = "/add_user")
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @DeleteMapping("/remove_user/{userId}")
    public String removeUser(@PathVariable int userId) {
        return userService.removeUser(userService.getUserById(userId));
    }


    @GetMapping("/list_of_users")
    public Map<Integer, User> getUsers() {
        return userService.getUsers();
    }


    // Test method for RestTemplate:
    @GetMapping(path = "/testRestTemplate")
    public String restTemplateTestMethod() {
        return "String from Rest Template Test Method!";
    }
}
