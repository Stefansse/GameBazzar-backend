package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);

    List<Order> findByUserId(Long userId);
}
