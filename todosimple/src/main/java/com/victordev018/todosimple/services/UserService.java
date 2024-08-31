package com.victordev018.todosimple.services;

import com.victordev018.todosimple.models.User;
import com.victordev018.todosimple.repositories.TaskRepository;
import com.victordev018.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    // dependencies
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return  userOptional.orElseThrow(() -> new RuntimeException(
                "User not found! id: " + id + ", Type: " + User.class.getName()
        ));
    }

    @Transactional  // do everything or do nothing
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional  // do everything or do nothing
    public User update(User obj){
        User newObject = findById(obj.getId());
        newObject.setPassword(obj.getPassword());
        return this.userRepository.save(newObject);
    }

    public void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("It is not possible to delete because there are related entities!");
        }
    }
}
