package com.hillel.task_management_system.service;


import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.exceptions.TaskExistsException;
import com.hillel.task_management_system.exceptions.TaskNullException;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private UserService userService;

    private final List<Task> notAssignedTasks = new ArrayList<>();

    private final Map<User, List<Task>> tasks = new HashMap<>();

    public String addTaskToNotAssignedTasksList(Task task) {
        if (task == null) {
            throw new TaskNullException("Error: Can't add task to not assigned tasks list. Task is NULL");
        } else if (notAssignedTasks.contains(task)) {
            throw new TaskExistsException("Error: Can't add task to not assigned tasks list. Task has already exist!");
        }else {
            notAssignedTasks.add(task);
            return "Task has been added successfully!";
        }
    }

    public List<Task> showNotAssignedTasks() {
        for (Task t : notAssignedTasks) {
            System.out.println(t);
        }
        return notAssignedTasks;
    }


    public void assignTaskToUser(int userId, Task task) {
        User user = userService.getUserById(userId);
        if (task != null && userService != null) {
            if (user != null) {
                List<Task> userTasks = tasks.computeIfAbsent(user, k -> new ArrayList<>());
                notAssignedTasks.remove(task);
                userTasks.add(task);
            } else {
                System.out.println("Error: Can't assign task to user. User with id: " + userId + " is null!");
            }
        } else {
            System.out.println("Error: Can't assign task to user. Task or UserService is null!");
        }
    }


    public List<Task> showUserTasks(User user) {
        List<Task> userTasks = tasks.get(user);
        if (user != null) {
            if (userTasks == null || userTasks.isEmpty()) {
                System.out.println("No tasks for : " + user.getName());
            } else {
                System.out.println("List of tasks for -> " + user);
                for (Task t : userTasks) {
                    System.out.println(t);
                }
            }
        } else {
            System.out.println("Error: Can't show user tasks. User is null!");
        }
        return userTasks;
    }

    public void changeTaskStatus(int userId, int taskId, Status status) {
        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("Error: Can't change task status. User with id: " + userId + " is null!");
        } else {
            List<Task> userTasks = tasks.get(user);
            if (userTasks != null && !userTasks.isEmpty()) {
                for (Task t : userTasks) {
                    if (t.getId() == taskId) {
                        t.setStatus(status);
                    }
                }
            } else {
                System.out.println("Error: No tasks found for user.");
            }
        }
    }

    public List<Task> findTaskByStatus(Status status) {
        List<Task> allFoundedTasks = new ArrayList<>();
        for (User user : tasks.keySet()) {
            List<Task> foundedTasks = new ArrayList<>();
            List<Task> userTasks = tasks.get(user);
            if (user == null && userTasks.isEmpty()) {
                System.out.println("Error: Can't find task by status. User is null or list of user's tasks is empty.");
            } else {
                for (Task t : userTasks) {
                    if (t.getStatus().equals(status)) {
                        foundedTasks.add(t);
                        allFoundedTasks.add(t);
                    }
                }
                if (!foundedTasks.isEmpty()) {
                    System.out.println(user + " has task(s) with status: "
                            + status + "\n" + foundedTasks);
                }
            }
        }
        return allFoundedTasks;
    }

    public List<Task> findTaskByPriority(Priority priority) {
        List<Task> allFoundedTasks = new ArrayList<>();
        for (User user : tasks.keySet()) {
            List<Task> foundedTasks = new ArrayList<>();
            List<Task> userTasks = tasks.get(user);
            if (user == null && userTasks.isEmpty()) {
                System.out.println("Error: Can't find task by priority. User is null or list of user's tasks is empty.");
            } else {
                for (Task t : userTasks) {
                    if (t.getPriority().equals(priority)) {
                        foundedTasks.add(t);
                        allFoundedTasks.add(t);
                    }
                }
                if (!foundedTasks.isEmpty()) {
                    System.out.println(user + " has task(s) with priority: "
                            + priority + "\n" + foundedTasks);
                }
            }
        }
        return allFoundedTasks;
    }

    public List<Task> findTaskByDeadline(String deadline) {
        List<Task> allFoundedTasks = new ArrayList<>();
        for (User user : tasks.keySet()) {
            List<Task> foundedTasks = new ArrayList<>();
            List<Task> userTasks = tasks.get(user);
            if (user == null && userTasks.isEmpty()) {
                System.out.println("Error: Can't find task by deadline. User is null or list of user's tasks is empty.");
            } else {
                for (Task t : userTasks) {
                    if (t.getDeadline().equals(deadline)) {
                        foundedTasks.add(t);
                        allFoundedTasks.add(t);
                    }
                }
                if (!foundedTasks.isEmpty()) {
                    System.out.println(user + " has task(s) with deadline: "
                            + deadline + "\n" + foundedTasks);
                }
            }
        }
        return allFoundedTasks;
    }

    public void deleteUserFromListOfTask(User user) {
        if (user == null) {
            System.out.println("Error. Can't delete user from list of tasks. User is null!");
        } else {
            for (Task t : tasks.get(user)) {
                addTaskToNotAssignedTasksList(t);
            }
            tasks.remove(user);
            userService.removeUser(user);
        }
    }
}
