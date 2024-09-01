package com.victordev018.todosimple.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity     // treats it as an entity ready to make a CRUD
@Table(name = User.TABLE_NAME)  // treat with a table of the database
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // no return list task, only write
    private List<Task> tasks = new ArrayList<>();

}
