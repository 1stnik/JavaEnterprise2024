package com.hillel.task_management_system.repository;

import com.hillel.task_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDaoJpa extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (id, name) VALUES (:id, :name)", nativeQuery = true)
    void addUser(@Param("id") int id, @Param("name") String name);

    User findUserById(int id);

    @Modifying
    @Transactional
    void removeUserById(@Param("id") int id);

    List<User> findAll();


}
