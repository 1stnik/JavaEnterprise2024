package com.hillel.task_management_system.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hillel.task_management_system.enums.Priority;
import com.hillel.task_management_system.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_id")
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
