package com.victordev018.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity     // treats it as an entity ready to make a CRUD
@Table(name = User.TABLE_NAME)  // treat with a table of the database
public class User {

    // validation groups
    public interface CreateUser {};
    public interface UpdateUser {};

    // table name
    public static final String TABLE_NAME = "tb_user";

    // attributes

    @Id                                                    // this attribute is id e primary key from table
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // auto increment
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(min = 2, max = 100, groups = CreateUser.class)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // no return password, only write
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(min = 8, max = 60, groups = {CreateUser.class, UpdateUser.class})
    private String password;

    @OneToMany(mappedBy = "user")   // mappedBy variable user from Task
    private List<Task> tasks = new ArrayList<>();

    // constructors
    public User(){
    }

    public User(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){this.id = id;}

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (!(obj instanceof User))
            return false;

        User user = (User) obj;
        if (this.id == null) {
            if (((User) obj).id != null){
                return false;
            } else if (!    this.id.equals(user.id)) {
                return false;
            }
        }
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null)? 0 : id.hashCode());
        return result;
    }
}
