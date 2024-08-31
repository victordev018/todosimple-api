package com.victordev018.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {

    // table name
    public static final String TABLE_NAME = "tb_task";

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)   // FK -> foreign key
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min =1 , max = 255)
    private String description;

    // constructors
    public Task(){
    }

    public Task(Long id, User user, String description){
        this.id = id;
        this.user = user;
        this.description = description;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (!(obj instanceof Task))
            return false;

        Task task = (Task) obj;
        if (this.id == null) {
            if (((Task) obj).id != null){
                return false;
            } else if (!this.id.equals(task.id)) {
                return false;
            }
        }
        return Objects.equals(id, task.id) && Objects.equals(user, task.user) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null)? 0 : id.hashCode());
        return result;
    }
}
