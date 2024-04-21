package com.hillel.task_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceFactory {

    @Autowired
    private TaskServiceJdbc taskServiceJdbc;

    @Autowired
    private TaskServiceJpa taskServiceJpa;

    @Value("${application.component}")
    private String componentValue;

    public TaskService createTaskService() {
        if(componentValue.equals("jdbc")) {
            return taskServiceJdbc;
        } else if (componentValue.equals("jpa")) {
            return taskServiceJpa;
        } else {
            throw new IllegalArgumentException("Unknown application.component value! " + componentValue);
        }
    }

}
