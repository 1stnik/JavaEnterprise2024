package com.hillel.task_management_system.dao;

import com.hillel.task_management_system.config.ConnectionConfig;
import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.exceptions.TaskSqlException;
import com.hillel.task_management_system.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDao {

    @Autowired
    private ConnectionConfig connectionConfig;


    public void addTaskToDatabase(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (task_id, title, description, deadline, priority, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, task.getTaskId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getDeadline());
            statement.setString(5, task.getPriority().getValue());
            statement.setString(6, task.getStatus().getValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to add task (Dao)");
        }
    }


    public List<Task> getAllTasks() throws TaskSqlException {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt("task_Id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("deadline"),
                        Priority.fromValue(resultSet.getString("priority")),
                        Status.fromValue(resultSet.getString("status")),
                        resultSet.getInt("user_Id"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to retrieve tasks from database", e);
        }
        return tasks;
    }


    public void assignTaskToUser(int userId, int taskId) throws TaskSqlException {
        String sql = "UPDATE tasks SET user_id = ? WHERE task_Id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to assign task to user (Dao)");
        }
    }


    public List<Task> getUserTasks(int userId) throws SQLException {
        List<Task> userTasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    userTasks.add(new Task(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("deadline"),
                            Priority.fromValue(resultSet.getString("priority")),
                            Status.fromValue(resultSet.getString("status")),
                            resultSet.getInt("user_Id")));
                }
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to get user task (Dao)");
        }
        return userTasks;
    }


    public List<Task> getTasksByDeadline(String deadline) throws TaskSqlException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE deadline = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setString(1, deadline);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tasks.add(new Task(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("deadline"),
                            Priority.fromValue(resultSet.getString("priority")),
                            Status.fromValue(resultSet.getString("status")),
                            resultSet.getInt("user_Id")));
                }
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to get tasks by Deadline from DB");
        }
        return tasks;
    }


    public List<Task> getTasksByPriority(Priority priority) throws TaskSqlException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE priority = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setString(1, priority.getValue());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tasks.add(new Task(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("deadline"),
                            Priority.fromValue(resultSet.getString("priority")),
                            Status.fromValue(resultSet.getString("status")),
                            resultSet.getInt("user_Id")));
                }
            } catch (SQLException e) {
                throw new TaskSqlException("Error: ResultSet failed");
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to get tasks by Priority from DB");
        }
        return tasks;
    }


    public List<Task> getTasksByStatus(Status status) throws TaskSqlException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE status = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setString(1, status.getValue());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tasks.add(new Task(
                            resultSet.getInt("task_Id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("deadline"),
                            Priority.fromValue(resultSet.getString("priority")),
                            Status.fromValue(resultSet.getString("status")),
                            resultSet.getInt("user_Id")));
                }
            } catch (SQLException e) {
                throw new TaskSqlException("Error: ResultSet failed");
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to get tasks by Status from DB");
        }
        return tasks;
    }


    public void changeTaskStatus(int userId, int taskId, Status status) throws SQLException {
        String sql = "UPDATE tasks SET status = ? WHERE task_Id = ? AND user_id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setString(1, status.getValue());
            statement.setInt(2, taskId);
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to update Task Status in the DB");
        }
    }


    // Service method
    public boolean taskExists(int taskId) throws SQLException {
        String sql = "SELECT EXISTS (SELECT 1 FROM tasks WHERE task_id = ?)";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            } catch (SQLException e) {
                throw new TaskSqlException("Error: ResultSet failed");
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to check task existing in DB");
        }
        return false;
    }


    // Service method
    public boolean taskIsAssigned(int taskId) throws SQLException {
        String sql = "SELECT user_id FROM tasks WHERE task_Id = ?";
        try (PreparedStatement statement = connectionConfig.getConnection().prepareStatement(sql)) {
            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer userId = (Integer) resultSet.getObject("user_id");
                    if (userId != null && userId > 0) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                throw new TaskSqlException("Error: ResultSet failed");
            }
        } catch (SQLException e) {
            throw new TaskSqlException("Error: Failed to check Task assignation!");
        }
        return false;
    }

}
