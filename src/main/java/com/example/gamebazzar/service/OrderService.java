package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.GameDTO;
import com.example.gamebazzar.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order addOrder(Order order);

    Optional<Order> findOrderById(Long orderId);

    List<Order> findAllOrders();

    void deleteOrderById(Long orderId);

    List<Order> getOrdersByUserId(Long userId);
}
