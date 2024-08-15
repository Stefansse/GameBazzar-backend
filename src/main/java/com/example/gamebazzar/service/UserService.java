package com.example.gamebazzar.service;

import com.example.gamebazzar.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Find a User by ID
    Optional<User> findUserById(Long userId);

    // Find a User by Email
    Optional<User> findUserByEmail(String email);

    // Get all Users
    List<User> findAllUsers();

    // Delete a User by ID
    void deleteUserById(Long userId);

    UserDetailsService userDetailsService();
}
