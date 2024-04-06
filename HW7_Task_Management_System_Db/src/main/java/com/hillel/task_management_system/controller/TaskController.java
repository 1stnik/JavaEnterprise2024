package com.hillel.task_management_system.controller;

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.service.TaskService;
import com.hillel.task_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/add_task")
    public String addTaskToNotAssignedTasksList(@RequestBody Task task) {
        return taskService.addTaskToNotAssignedTasksList(task);
    }

    @GetMapping("/get_not_assigned_tasks")
    public List<Task> showNotAssignedTasks() {
        return taskService.showNotAssignedTasks();
    }

    @PostMapping("/assign_task_to_user/{userId}")
    public String assignTaskToUser(@PathVariable int userId, @RequestBody Task task) {
        taskService.assignTaskToUser(userId, task);
        return "Task assigned to user successfully!";
    }

    @GetMapping("/get_user_tasks/{userId}")
    public List<Task> showUserTasks(@PathVariable int userId) {
        return taskService.showUserTasks(userService.getUserById(userId));
    }

    @PutMapping("/change_status/{userId}/{taskId}")
    public String changeTaskStatus(@PathVariable int userId, @PathVariable int taskId, @RequestBody Status status) {
        taskService.changeTaskStatus(userId, taskId, status);
        return "Task status has been changed successfully!";
    }

    @GetMapping("/find_by_status/{status}")
    public List<Task> findTaskByStatus(@PathVariable("status") String statusStr) {
        Status status = Status.fromValue(statusStr);
        return taskService.findTaskByStatus(status);
    }

    @GetMapping("/find_by_priority/{priority}")
    public List<Task> findTaskByPriority(@PathVariable("priority") String priorityStr) {
        Priority priority = Priority.fromValue(priorityStr);
        return taskService.findTaskByPriority(priority);
    }

    @GetMapping("/find_by_deadline/{deadline}")
    public List<Task> findTaskByDeadline(@PathVariable String deadline) {
        return taskService.findTaskByDeadline(deadline);
    }

    @DeleteMapping("/delete_user/{userId}")
    public String deleteUserFromListOfTask(@PathVariable int userId) {
        taskService.deleteUserFromListOfTask(userService.getUserById(userId));
        return "User has been deleted successfully!";
    }
}
