package com.hillel.task_management_system.repository;

import com.hillel.task_management_system.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskDaoJpa extends JpaRepository<Task, Integer> {

    @Modifying
    @Query(value = "INSERT INTO tasks (task_id, title, description, deadline, priority, status) " +
            "VALUES (:taskId, :title, :description, :deadline, :priority, :status)", nativeQuery = true)
    @Transactional
    void addTaskToDatabase(@Param("taskId") int taskId,
                           @Param("title") String title,
                           @Param("description") String description,
                           @Param("deadline") String deadline,
                           @Param("priority") String priority,
                           @Param("status") String status);

    List<Task> findAll();

    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET user_id = :userId WHERE task_Id = :taskId", nativeQuery = true)
    void assignTaskToUser(@Param("userId") int userId, @Param("taskId") int taskId);

    List<Task> findTasksByUserId(int userId);

    List<Task> findTasksByDeadline(String deadline);

    @Query(value = "SELECT * FROM tasks WHERE priority = :priorityValue", nativeQuery = true)
    List<Task> findTasksByPriority(@Param("priorityValue") String priorityValue);

    @Query(value = "SELECT * FROM tasks WHERE status = :statusValue", nativeQuery = true)
    List<Task> findTasksByStatus(@Param("statusValue") String statusValue);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET status = :statusValue WHERE task_id = :taskId AND user_id = :userId", nativeQuery = true)
    void changeTaskStatus(@Param("userId") int userId, @Param("taskId") int taskId, @Param("statusValue") String statusValue);

    @Modifying
    @Transactional
    void removeTaskByTaskId(int taskId);

}
