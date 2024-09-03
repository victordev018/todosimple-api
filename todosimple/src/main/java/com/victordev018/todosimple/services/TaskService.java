package com.victordev018.todosimple.services;

import com.victordev018.todosimple.models.Task;
import com.victordev018.todosimple.models.User;
import com.victordev018.todosimple.repositories.TaskRepository;
import com.victordev018.todosimple.services.exceptions.DataBindingViolationException;
import com.victordev018.todosimple.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return taskOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Task not found id: " + id + ", Type: " + Task.class.getName()
        ));
    }

    public List<Task> findAllUserId(Long id){
        List<Task> tasks = this.taskRepository.findByUser_Id(id);
        return tasks;
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

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        }
        catch (Exception e){
            throw new DataBindingViolationException("It is not possible to delete because there are related entities!");
        }
    }
}
