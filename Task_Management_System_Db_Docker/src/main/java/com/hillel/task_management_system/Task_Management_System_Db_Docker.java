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

import java.sql.SQLException;

@SpringBootApplication
public class Task_Management_System_Db_Docker {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(Task_Management_System_Db_Docker.class, args);

        // Get beans
        UserService userService = applicationContext.getBean(UserService.class);
        TaskService taskService = applicationContext.getBean(TaskService.class);


        // Add users
        System.out.println("\n----------------------------------Add users-------------------------------------- ");

        User user1 = new User(1, "First User");
        User user2 = new User(2, "Second User");
        User user3 = new User(3, "Third User");
        User user4 = new User(4, "Fourth User");

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);


        // Add tasks
        System.out.println("\n-----------------------Add tasks to list of Not Assigned tasks----------------------- ");

        Task task1 = new Task(1001, "Task 1", "Task one description", "2024-06-01", Priority.MEDIUM, Status.NEW);
        Task task2 = new Task(1002, "Task 2", "Task two description", "2024-06-02", Priority.LOW, Status.NEW);
        Task task3 = new Task(1003, "Task 3", "Task three description", "2024-06-03", Priority.HIGH, Status.NEW);
        Task task4 = new Task(1004, "Task 4", "Task four description", "2024-06-04", Priority.HIGH, Status.NEW);
        Task task5 = new Task(1005, "Task 5", "Task five description", "2024-06-05", Priority.MEDIUM, Status.NEW);
        Task task6 = new Task(1006, "Task 6", "Task six description", "2024-06-06", Priority.HIGH, Status.NEW);
        Task task7 = new Task(1007, "Task 7", "Task seven description", "2024-06-07", Priority.LOW, Status.NEW);
        Task task8 = new Task(1008, "Task 8", "Task eight description", "2024-06-08", Priority.HIGH, Status.NEW);
        Task task9 = new Task(1009, "Task 9", "Task nine description", "2024-06-09", Priority.MEDIUM, Status.NEW);

        taskService.addTaskToDatabase(task1);
        taskService.addTaskToDatabase(task2);
        taskService.addTaskToDatabase(task3);
        taskService.addTaskToDatabase(task4);
        taskService.addTaskToDatabase(task5);
        taskService.addTaskToDatabase(task6);
        taskService.addTaskToDatabase(task7);
        taskService.addTaskToDatabase(task8);
        taskService.addTaskToDatabase(task9);

    }
}
