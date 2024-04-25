package com.hillel.task_management_system.controller;

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/add_task")
    public String addTaskToDb(@RequestBody Task task) throws SQLException {
        taskService.addTaskToDatabase(task);
        return "Task added to DB successfully!";
    }

    @GetMapping("/get_all_tasks")
    public List<Task> showAllTasks() throws SQLException {
        return taskService.getAllTasks();
    }

    @PostMapping("/assign_task_to_user/{userId}/{taskId}")
    public String assignTaskToUser(@PathVariable int userId, @PathVariable int taskId) throws SQLException {
        return taskService.assignTaskToUser(userId, taskId);
    }

    @GetMapping("/get_user_tasks/{userId}")
    public List<Task> showUserTasks(@PathVariable int userId) throws SQLException {
        return taskService.getUserTasks(userId);
    }

    @PutMapping("/change_status/{userId}/{taskId}")
    public String changeTaskStatus(@PathVariable int userId, @PathVariable int taskId, @RequestBody String statusStr) throws SQLException {
        Status status = Status.fromValue(statusStr);
        return taskService.changeTaskStatus(userId, taskId, status);
    }

    @GetMapping("/find_by_status/{status}")
    public List<Task> findTaskByStatus(@PathVariable("status") String statusStr) throws SQLException {
        Status status = Status.fromValue(statusStr);
        return taskService.findTaskByStatus(status);
    }

    @GetMapping("/find_by_priority/{priority}")
    public List<Task> findTaskByPriority(@PathVariable("priority") String priorityStr) throws SQLException {
        Priority priority = Priority.fromValue(priorityStr);
        return taskService.findTaskByPriority(priority);
    }

    @GetMapping("/find_by_deadline/{deadline}")
    public List<Task> findTaskByDeadline(@PathVariable String deadline) throws SQLException {
        return taskService.findTaskByDeadline(deadline);
    }

}
