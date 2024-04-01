package com.hillel.task_management_system.model;

import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;

public class Task {

    private final Integer id;

    private final String title;

    private final String description;

    private final String deadline;

    private final Priority priority;

    private Status status;

    public Task(Integer id, String title, String description, String deadline, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = Status.NEW;
    }

    public Integer getId() {
        return id;
    }

    // Do not delete, need for REST!
    public String getTitle() {
        return title;
    }

    // Do not delete, need for REST!
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

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
