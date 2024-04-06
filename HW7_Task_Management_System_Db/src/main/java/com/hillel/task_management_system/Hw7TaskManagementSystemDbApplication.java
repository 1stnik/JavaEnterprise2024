package com.hillel.task_management_system;

/**
 * Завдання: Реалізуйте можливість створювати нові завдання з вказанням назви, опису, терміну виконання та пріоритету.
 * Користувачі: Додавання ти видалення користувачів
 * Призначення завдань: Додайте можливість призначати завдання іншим користувачам. Користувачі можуть відстежувати призначені їм завдання та їх статус.
 * Статус завдань: Реалізуйте різні статуси для завдань (наприклад, "нове", "в роботі", "завершене", "відкладене" тощо) та можливість їх зміни.
 * Пошук та фільтрація: Додайте можливість шукати та фільтрувати завдання за різними критеріями, такими як статус, пріоритет, термін виконання тощо.
 * Зберігання завдань та користувачів зробити в колекціях (самостійно обрати необхідні колекціі)
 * Сервіси з логікою створити як біни та визвати через ApplicationContext.
 */

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.model.User;
import com.hillel.task_management_system.service.TaskService;
import com.hillel.task_management_system.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Hw7TaskManagementSystemDbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(Hw7TaskManagementSystemDbApplication.class, args);

        // Get beans
        UserService userService = applicationContext.getBean(UserService.class);
        TaskService taskService = applicationContext.getBean(TaskService.class);


        // Add users
        System.out.println("\n----------------------------------Add users-------------------------------------- ");
        System.out.println("List of users: ");
        User user1 = new User(1, "First User");
        User user2 = new User(2, "Second User");
        User user3 = new User(3, "Third User");
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        for (User user : userService.getUsers().values()) {
            System.out.println(user);
        }

        // Add tasks
        System.out.println("\n-----------------------Add tasks to list of Not Assigned tasks----------------------- ");

        Task task1 = new Task(100001, "Task 1", "Task one description", "2024-06-01", Priority.MEDIUM);
        Task task2 = new Task(100002, "Task 2", "Task two description", "2024-06-02", Priority.LOW);
        Task task3 = new Task(100003, "Task 3", "Task three description", "2024-06-03", Priority.HIGH);
        Task task4 = new Task(100004, "Task 4", "Task four description", "2024-06-04", Priority.HIGH);
        Task task5 = new Task(100005, "Task 5", "Task five description", "2024-06-05", Priority.MEDIUM);
        Task task6 = new Task(100006, "Task 6", "Task six description", "2024-06-06", Priority.HIGH);
        Task task7 = new Task(100007, "Task 7", "Task seven description", "2024-06-07", Priority.LOW);
        Task task8 = new Task(100008, "Task 8", "Task eight description", "2024-06-08", Priority.HIGH);
        taskService.addTaskToNotAssignedTasksList(task1);
        taskService.addTaskToNotAssignedTasksList(task2);
        taskService.addTaskToNotAssignedTasksList(task3);
        taskService.addTaskToNotAssignedTasksList(task4);
        taskService.addTaskToNotAssignedTasksList(task5);
        taskService.addTaskToNotAssignedTasksList(task6);
        taskService.addTaskToNotAssignedTasksList(task7);
        taskService.addTaskToNotAssignedTasksList(task8);
        taskService.showNotAssignedTasks();


        // Assign tasks to users
        System.out.println("\n-------------------------------Assign tasks to users---------------------------------- ");

        taskService.assignTaskToUser(1, task1);
        taskService.assignTaskToUser(3, task2);
        taskService.assignTaskToUser(2, task3);
        taskService.assignTaskToUser(1, task4);
        taskService.assignTaskToUser(2, task5);
        taskService.assignTaskToUser(2, task6);
        taskService.assignTaskToUser(3, task7);
        taskService.assignTaskToUser(1, task8);
        taskService.showUserTasks(user1);
        taskService.showUserTasks(user2);
        taskService.showUserTasks(user3);

        System.out.println("\nNot assigned tasks list:");
        taskService.showNotAssignedTasks();


        // Change task(s) status
        System.out.println("\n-------------------------------Change task(s) status---------------------------------- ");

        taskService.changeTaskStatus(3, 100002, Status.COMPLETE);
        taskService.changeTaskStatus(2, 100005, Status.IN_PROGRESS);
        taskService.changeTaskStatus(1, 100004, Status.PENDING);
        taskService.changeTaskStatus(1, 100008, Status.IN_PROGRESS);
        taskService.changeTaskStatus(2, 100006, Status.PENDING);
        taskService.changeTaskStatus(1, 100001, Status.COMPLETE);
        taskService.showUserTasks(user1);
        taskService.showUserTasks(user2);
        taskService.showUserTasks(user3);


        // Find task(s) by a parameter
        System.out.println("\n---------------------------Find task(s) by a parameter------------------------------- ");

        taskService.findTaskByStatus(Status.IN_PROGRESS);
        System.out.println("-----------------------");
        taskService.findTaskByPriority(Priority.MEDIUM);
        System.out.println("-----------------------");
        taskService.findTaskByDeadline("2024-06-03");


        // Delete user
        System.out.println("\n----------------------------------Delete user-------------------------------------- ");


        taskService.deleteUserFromListOfTask(user2);
        System.out.println("List of users: ");
        for (User user : userService.getUsers().values()) {
            System.out.println(user);
        }

        System.out.println("\n----------------------------------After delete user-------------------------------------- ");

        System.out.println("\nUsers tasks after user deleting:");
        taskService.showUserTasks(user1);
        taskService.showUserTasks(user2);
        taskService.showUserTasks(user3);

        System.out.println("\nNot assigned tasks list:");
        taskService.showNotAssignedTasks();

        System.out.println("\nList of users after user deleting: ");
        for (User user : userService.getUsers().values()) {
            System.out.println(user);
        }

        // Add users
        System.out.println("\n----------------------------------Add users-------------------------------------- ");

        System.out.println("List of users: ");
        User user4 = new User(4, "Fourth User");
        userService.addUser(user4);
        for (User user : userService.getUsers().values()) {
            System.out.println(user);
        }

        System.out.println("\n-----------------------Add tasks to list of Not Assigned tasks----------------------- ");

        Task task9 = new Task(100009, "Task 9", "Task nine description", "2024-06-09", Priority.MEDIUM);
        taskService.addTaskToNotAssignedTasksList(task9);
        taskService.showNotAssignedTasks();

        System.out.println("\n-------------------------------Assign tasks to users---------------------------------- ");

        taskService.assignTaskToUser(4, task9);
        taskService.assignTaskToUser(4, task5);
        System.out.println("\nNot assigned tasks list:");
        taskService.showNotAssignedTasks();

        System.out.println("\n-------------------------------List of User's tasks after adding task to user----------");
        taskService.showUserTasks(user1);
        taskService.showUserTasks(user3);
        taskService.showUserTasks(user4);

    }

}
