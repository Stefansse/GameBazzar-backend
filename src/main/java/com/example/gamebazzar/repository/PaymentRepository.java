package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrder(Order order);

    List<Payment> findByOrderOrderId(Long orderId);
}


