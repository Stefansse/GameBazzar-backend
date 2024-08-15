package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.PaymentDTO;
import com.example.gamebazzar.model.Payment;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PaymentDTO paymentDTO);
    Payment getPaymentById(Long paymentId);
    List<Payment> getPaymentsByOrderId(Long orderId);
    List<Payment> findAllPayments();
    Payment updatePayment(Long paymentId, String paymentStatus);
    void deletePayment(Long paymentId);
}
