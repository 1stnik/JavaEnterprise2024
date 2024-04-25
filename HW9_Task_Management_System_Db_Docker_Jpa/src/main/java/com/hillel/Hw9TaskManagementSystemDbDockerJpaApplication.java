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

    }
}
