package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.GameDTO;
import com.example.gamebazzar.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    // Create or update an order
    Order addOrder(Order order);

    // Find an order by its ID
    Optional<Order> findOrderById(Long orderId);

    // Get all orders
    List<Order> findAllOrders();

    // Delete an order by its ID
    void deleteOrderById(Long orderId);

    List<Order> getOrdersByUserId(Long userId);
}
