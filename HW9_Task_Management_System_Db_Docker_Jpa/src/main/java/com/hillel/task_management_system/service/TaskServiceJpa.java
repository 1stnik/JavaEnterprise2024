package com.hillel.task_management_system.service;

import com.hillel.task_management_system.dao.TaskDao;
import com.hillel.task_management_system.dao.UserDao;
import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.exceptions.TaskNullException;
import com.hillel.task_management_system.exceptions.TaskSqlException;
import com.hillel.task_management_system.exceptions.UserNullException;
import com.hillel.task_management_system.exceptions.UserSqlException;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.repository.TaskDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app.connection", name = "type", havingValue = "jpa")
public class TaskServiceJpa implements TaskService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskDaoJpa taskDaoJpa;


    public void addTaskToDatabase(Task task) throws SQLException {
        if (task == null) {
            throw new TaskNullException("Error: Can't add task to DB. Task is NULL!");
        } else if (taskDao.taskExists(task.getTaskId())) {
            throw new TaskSqlException("Error: Can't add task to DB. Task has already exist!");
        } else {
            taskDaoJpa.addTaskToDatabase(
                    task.getTaskId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDeadline(),
                    task.getPriority().getValue(),
                    task.getStatus().getValue());
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        if (taskDao.getAllTasks() == null) {
            throw new UserSqlException("Error: Can't get tasks from DB. List of tasks is NULL!");
        }

        //TODO Delete string below
        System.out.println("You get all tasks with JPA successfully!!!!!!");

        return taskDaoJpa.findAll();
    }


    public String assignTaskToUser(int userId, int taskId) throws SQLException {
        if (!taskDao.taskExists(taskId)) {
            throw new TaskSqlException("Error: Task with ID " + taskId + " does not exist!");
        } else if (!userDao.userExists(userId)) {
            throw new TaskSqlException("Error: User with ID " + userId + " does not exist!");
        } else if (taskDao.taskIsAssigned(taskId)) {
            throw new TaskSqlException("Error: Task with ID " + taskId + " is already assigned to a user!");
        } else {
            taskDaoJpa.assignTaskToUser(userId, taskId);
            return "Task with ID " + taskId + " assigned to User with ID " + userId + " successfully!";
        }
    }


    public List<Task> getUserTasks(int userId) throws SQLException {
        if (!userDao.userExists(userId)) {
            throw new TaskSqlException("Error: Can't get User's tasks. User doesn't exist!");
        } else if (userDao.getUserById(userId) == null) {
            throw new UserNullException("Error: Can't get user from DB. User is NULL!");
        } else {
            if (taskDao.getUserTasks(userId).isEmpty()) {
                throw new TaskSqlException("No tasks for User with ID: " + userId);
            }
            return taskDaoJpa.findTasksByUserId(userId);
        }
    }

    public List<Task> findTaskByDeadline(String deadline) throws SQLException {
        if (deadline == null) {
            throw new TaskNullException("Error: Request string is NULL!");
        }
        return taskDaoJpa.findTasksByDeadline(deadline);
    }


    public List<Task> findTaskByPriority(Priority priority) throws SQLException {
        if (priority == null) {
            throw new TaskNullException("Error: Request PRIORITY is NULL!");
        }
        return taskDaoJpa.findTasksByPriority(priority.getValue());
    }

    public List<Task> findTaskByStatus(Status status) throws SQLException {
        if (status == null) {
            throw new TaskNullException("Error: Request STATUS is NULL!");
        }
        return taskDaoJpa.findTasksByStatus(status.getValue());

    }

    public String changeTaskStatus(int userId, int taskId, Status status) throws SQLException {
        if (!userDao.userExists(userId)) {
            throw new UserSqlException("Error: Can't get User's tasks. User doesn't exist!");
        } else if (userDao.getUserById(userId) == null) {
            throw new UserNullException("Error: Can't remove user from DB. User is NULL!");
        } else if (!taskDao.taskExists(taskId)) {
            throw new TaskSqlException("Error: Can't get Task from DB. Task doesn't exist!");
        } else {
            taskDaoJpa.changeTaskStatus(userId, taskId, status.getValue());
            return "Task status has been changed successfully!";
        }
    }
}
