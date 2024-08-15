package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}