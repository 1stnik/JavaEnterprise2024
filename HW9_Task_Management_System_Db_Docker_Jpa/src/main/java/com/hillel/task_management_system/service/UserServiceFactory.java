package com.hillel.task_management_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFactory {

    @Autowired
    private UserServiceJdbc userServiceJdbc;

    @Autowired
    private UserServiceJpa userServiceJpa;

    @Value("${application.component}")
    private String componentValue;

    public UserService createUserService() {
        if(componentValue.equals("jdbc")) {
            return userServiceJdbc;
        } else if (componentValue.equals("jpa")) {
            return userServiceJpa;
        } else {
            throw new IllegalArgumentException("Unknown application.component value! " + componentValue);
        }
    }
}
