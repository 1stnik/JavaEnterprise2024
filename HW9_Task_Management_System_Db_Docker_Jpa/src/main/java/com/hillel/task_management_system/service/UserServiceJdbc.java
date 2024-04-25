package com.hillel.task_management_system.service;

import com.hillel.task_management_system.dao.UserDao;
import com.hillel.task_management_system.exceptions.UserNullException;
import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.User;
import com.hillel.task_management_system.repository.UserDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jdbc")
public class UserServiceJdbc implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoJpa userDaoJpa;

    public String addUser(User user) throws SQLException {
        if (user == null) {
            throw new UserNullException("Error: Can't add user to DB. User is NULL.");
        } else if (userDao.userExists(user.getId())) {
            throw new UserSqlException("Error: User with id = " + user.getId() + " has already existed in DataBase!");
        } else {
            userDao.addUser(user);
            return "User with id = " + user.getId() + " and name = '" + user.getName()
                    + "' was added to DB successfully!";
        }
    }

    public User getUserById(int userId) throws SQLException {
        if (!userDao.userExists(userId)) {
            throw new UserSqlException("Error: Can't get user from DB. User with id = " + userId
                    + " doesn't exist in DB.");
        } else {
            return userDao.getUserById(userId);
        }
    }

    public String removeUser(int userId) throws SQLException {
        if (!userDao.userExists(userId)) {
            throw new UserSqlException("Error: Can't remove user from DB. User with id = " + userId
                    + " doesn't exist in DB.");
        }
        else if (userDao.getUserById(userId) == null) {
            throw new UserNullException("Error: Can't remove user from DB. User is NULL.");
        }
        else {
            userDao.removeUser(userId);
            return "User with id = " + userId + "' had been removed from DB successfully!";
        }
    }

    public List<User> getAllUsers() throws SQLException {
        if (userDao.getUsers() == null) {
            throw new UserNullException("Error: Can't get user from DB. List of users is NULL.");
        } else {

            //TODO Delete string below
            System.out.println("You get all users with JDBC successfully!!!!!!");

            return userDao.getUsers();
        }
    }
}