package com.hillel.task_management_system.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;

public class Task {

    private final Integer taskId;

    private final String title;

    private final String description;

    private final String deadline;

    private final Priority priority;

    private final Status status;

    private Integer userId;


    @JsonCreator
    public Task(@JsonProperty("taskId") Integer taskId,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description,
                @JsonProperty("deadline") String deadline,
                @JsonProperty("priority") Priority priority,
                @JsonProperty("status") Status status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
    }


    public Task(Integer taskId, String title, String description, String deadline, Priority priority, Status status, Integer userId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
