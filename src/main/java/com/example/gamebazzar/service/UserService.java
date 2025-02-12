package com.example.gamebazzar.service;

import com.example.gamebazzar.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long userId);

    Optional<User> findUserByEmail(String email);

    List<User> findAllUsers();


    void deleteUserById(Long userId);

    UserDetailsService userDetailsService();
}
