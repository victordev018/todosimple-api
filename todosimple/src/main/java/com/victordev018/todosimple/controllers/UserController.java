package com.victordev018.todosimple.controllers;

import com.victordev018.todosimple.models.User;
import com.victordev018.todosimple.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")       // mapped requests for /users
@Validated
public class UserController {

    // dependencies
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody  User obj){
        userService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<User> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        User user = userService.update(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

