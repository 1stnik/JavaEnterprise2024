package com.hillel.task_management_system.service;


import com.hillel.task_management_system.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {


    private final Map<Integer, User> users = new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        if (users.get(id) == null) {
            System.out.println("Error. Can't add user to list of users. User is null!");
            return new User(0, "Default Name");
        } else {
            return users.get(id);
        }
    }

    public void addUser(User user) {
        if (user == null) {
            System.out.println("Error. Can't add user to list of users. User is null!");
        } else {
            users.put(user.getId(), user);
        }
    }

    public void removeUser(User user) {
        if (user == null) {
            System.out.println("Error. Can't remove user from list of users. User is null!");
        } else {
            users.remove(user.getId());
        }
    }
}
