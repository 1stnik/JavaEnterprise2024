package com.hillel.task_management_system.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return name + " (user ID: " + id + ")";
    }
}
