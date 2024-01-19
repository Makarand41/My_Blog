package com.example.Blog04;

import java.util.Optional;


public interface UserService {
    Optional<User> getStudentById(Long id);
    // Other methods for Student-related business logic
}