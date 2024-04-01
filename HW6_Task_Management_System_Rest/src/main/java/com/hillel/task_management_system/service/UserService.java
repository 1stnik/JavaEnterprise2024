package com.hillel.task_management_system.service;

import com.hillel.task_management_system.exceptions.UserDoesntExistException;
import com.hillel.task_management_system.exceptions.UserExistsException;
import com.hillel.task_management_system.exceptions.UserNullException;
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
            throw new UserDoesntExistException("Error: Can't get user from DB. User with id = " + id + " doesn't exist in DB.");
        } else {
            return users.get(id);
        }
    }

    public String addUser(User user) {
        if (user == null) {
            throw new UserNullException("Error: Can't add user to DB. User is NULL.");
        }
        else if (users.containsKey(user.getId())) {
            throw new UserExistsException("Error: User with id = " + user.getId() + " has already existed in DataBase!");
        }
        else {
            users.put(user.getId(), user);
            return "User with id = " + user.getId() + " and name = '" + user.getName() + "' was added to DB successfully!";
        }
    }

    public String removeUser(User user) {
        if (user == null) {
            throw new UserNullException("Error: Can't remove user from DB. User is NULL.");
        } else if (!users.containsKey(user.getId())) {
            throw new UserDoesntExistException("Error: Can't remove user from DB. User with id = " + user.getId() + " doesn't exist in DB.");
        } else {
            users.remove(user.getId());
            return "User with id = " + user.getId() + " and name = '" + user.getName() + "' had been removed from DB successfully!";
        }
    }
}
