package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrder(Order order);
    List<OrderItem> findByGame(Game game);
}
