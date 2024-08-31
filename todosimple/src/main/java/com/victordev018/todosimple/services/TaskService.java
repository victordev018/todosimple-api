package com.victordev018.todosimple.services;

import com.victordev018.todosimple.models.Task;
import com.victordev018.todosimple.models.User;
import com.victordev018.todosimple.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {

    // dependencies
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.orElseThrow(() -> new RuntimeException(
                "Task not found id: " + id + ", Type: " + Task.class.getName()
        ));
    }

    @Transactional
    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        return this.taskRepository.save(obj);
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Task obj){
        findById(obj.getId());
        try {
            this.taskRepository.deleteById(obj.getId());
        }
        catch (Exception e){
            throw new RuntimeException("It is not possible to delete because there are related entities!");
        }
    }
}
