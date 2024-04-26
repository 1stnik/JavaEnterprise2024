package com.hillel.task_management_system.service;

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TaskService {

    void addTaskToDatabase(Task task) throws SQLException;

    List<Task> getAllTasks() throws SQLException;

    String assignTaskToUser(int userId, int taskId) throws SQLException;

    List<Task> getUserTasks(int userId) throws SQLException;

    List<Task> findTaskByDeadline(String deadline) throws SQLException;

    List<Task> findTaskByPriority(Priority priority) throws SQLException;

    List<Task> findTaskByStatus(Status status) throws SQLException;

    String changeTaskStatus(int userId, int taskId, Status status) throws SQLException;
}
