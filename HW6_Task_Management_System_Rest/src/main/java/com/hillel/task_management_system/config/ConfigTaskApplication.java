package com.hillel.task_management_system.config;

import com.hillel.task_management_system.service.TaskService;
import com.hillel.task_management_system.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTaskApplication {


    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

}

