package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.DTO.GameDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.repository.OrderItemRepository;
import com.example.gamebazzar.repository.OrderRepository;
import com.example.gamebazzar.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final GameRepository gameRepository;

    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, GameRepository gameRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.gameRepository = gameRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }


}
