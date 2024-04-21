package com.hillel.task_management_system.config;

import com.hillel.task_management_system.dao.TaskDao;
import com.hillel.task_management_system.dao.UserDao;
import com.hillel.task_management_system.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfigTaskApplication {


    @Bean
    public UserServiceJdbc userService() {
        return new UserServiceJdbc();
    }

    @Bean
    public TaskServiceJdbc taskService() {
        return new TaskServiceJdbc();
    }


    @Bean
    public TaskServiceJpa taskServiceJpa() {
        return new TaskServiceJpa();
    }

    @Bean
    public TaskServiceFactory taskFactory() {
        return new TaskServiceFactory();
    }


    @Bean
    public UserServiceJpa userServiceJpa() {
        return new UserServiceJpa();
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDao();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public ConnectionConfig connectionConfig() {
        return new ConnectionConfig();
    }

    @Bean
    public DataSource dbDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("08080808");
        return dataSource;
    }

}
