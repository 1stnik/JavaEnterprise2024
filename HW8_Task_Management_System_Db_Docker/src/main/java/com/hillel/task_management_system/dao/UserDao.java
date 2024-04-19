package com.hillel.task_management_system.dao;

import com.hillel.task_management_system.config.ConnectionConfig;
import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {


    @Autowired
    private ConnectionConfig connectionConfig;


    public void addUser(User user) throws UserSqlException {
        String sql = "INSERT INTO users (id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserSqlException("Error: Failed to add User (Dao)");
        }
    }


    public User getUserById(int id) throws UserSqlException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    return new User(id, name);
                }
            }
        } catch (SQLException e) {
            throw new UserSqlException("Error: Failed to get User by ID (Dao)");
        }
        return null;
    }


    public void removeUser(int id) throws UserSqlException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserSqlException("Error: Failed to remove User (Dao)");
        }
    }


    public List<User> getUsers() throws UserSqlException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            throw new UserSqlException("Error: Failed to get users (Dao)");
        }
        return users;
    }


    // Service method
    public boolean userExists(int userId) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM users WHERE id = ?)";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
        }
        return false;
    }
}
