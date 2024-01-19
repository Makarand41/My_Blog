package com.example.Blog04;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> getStudentById(Long id) {
        return repository.findById(id);
    }
    // Implement other methods
    
    
    
    
    
    
}
