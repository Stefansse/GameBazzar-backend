package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.DTO.PaymentDTO;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.Payment;
import com.example.gamebazzar.repository.OrderRepository;
import com.example.gamebazzar.repository.PaymentRepository;
import com.example.gamebazzar.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    public Payment createPayment(PaymentDTO paymentDTO) {
        // Fetch the order
        Order order = orderRepository.findById(paymentDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Create a new payment
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentDate(paymentDTO.getPaymentDate() != null ? paymentDTO.getPaymentDate() : LocalDate.now());
        payment.setPaymentAmount(order.getTotalAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());

        // Save the payment
        return paymentRepository.save(payment);
    }
    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return paymentRepository.findByOrderOrderId(orderId);
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePayment(Long paymentId, String paymentStatus) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setPaymentStatus(paymentStatus);
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentRepository.delete(payment);
    }
}
