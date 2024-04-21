package com.hillel;

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import com.hillel.task_management_system.model.Task;
import com.hillel.task_management_system.model.User;
import com.hillel.task_management_system.repository.TaskDaoJpa;
import com.hillel.task_management_system.repository.UserDaoJpa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hillel.task_management_system"})
public class Hw9TaskManagementSystemDbDockerJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Hw9TaskManagementSystemDbDockerJpaApplication.class, args);

//        TaskDaoJpa taskDaoJpa = applicationContext.getBean(TaskDaoJpa.class);
//        UserDaoJpa userDaoJpa = applicationContext.getBean(UserDaoJpa.class);
//
//        taskDaoJpa.addTaskToDatabase(
//                1013,
//                "Task 13",
//                "Task thirteen description",
//                "2024-06-13",
//                "LOW",
//                "NEW");
//
//        taskDaoJpa.assignTaskToUser(3, 1013);
//
//        for (Task t : taskDaoJpa.findAll()) {
//            System.out.println(t);
//        }
//
//        System.out.println("----ID-----------------------------------------------------------------------");
//
//        for (Task t : taskDaoJpa.findTasksByUserId(3)) {
//            System.out.println(t);
//        }
//
//        System.out.println("----Deadline----------------------------------------------------------------");
//
//        for (Task t : taskDaoJpa.findTasksByDeadline("2024-06-09")) {
//            System.out.println(t);
//        }
//
//        System.out.println("----Priority----------------------------------------------------------------");
//
//        for (Task t : taskDaoJpa.findTasksByPriority(Priority.HIGH.getValue())) {
//            System.out.println(t);
//        }
//
//        System.out.println("----Status------------------------------------------------------------------");
//
//        for (Task t : taskDaoJpa.findTasksByStatus(Status.NEW.getValue())) {
//            System.out.println(t);
//        }
//
//        taskDaoJpa.changeTaskStatus(3, 1013, Status.COMPLETE.getValue());
//
//        for (Task t : taskDaoJpa.findAll()) {
//            System.out.println(t);
//        }
//
//        taskDaoJpa.removeTaskByTaskId(1013);
//
//        System.out.println("----Add User----------------------------------------------------------------");
//
//        userDaoJpa.addUser(6, "Sixth");
//
//        for (User u : userDaoJpa.findAll()) {
//            System.out.println(u);
//        }
//
//        System.out.println("----Find User by ID---------------------------------------------------------");
//
//        System.out.println(userDaoJpa.findUserById(6));
//
//        System.out.println("----List of Users after deleting");
//
//        userDaoJpa.removeUserById(6);
//
//        for (User u : userDaoJpa.findAll()) {
//            System.out.println(u);
//        }
//
//

    }

}
