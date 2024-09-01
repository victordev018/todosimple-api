package com.victordev018.todosimple.controllers;

import com.victordev018.todosimple.dto.task.TaskResponseCreated;
import com.victordev018.todosimple.models.Task;
import com.victordev018.todosimple.services.TaskService;
import com.victordev018.todosimple.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    // dependencies
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task obj = this.taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long user_id){
        userService.findById(user_id);
        List<Task> obj = this.taskService.findAllUserId(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @PostMapping
    @Validated
    public ResponseEntity<TaskResponseCreated> create(@Valid @RequestBody Task obj){
        obj = this.taskService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskResponseCreated(obj.getId()));
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody Task obj){
        obj.setId(id);
        Task task = this.taskService.update(obj);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
