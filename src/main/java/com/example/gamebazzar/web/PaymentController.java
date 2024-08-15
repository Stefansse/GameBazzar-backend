package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.PaymentDTO;
import com.example.gamebazzar.model.Payment;
import com.example.gamebazzar.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create a new payment with JSON body
    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(payment);
    }
    // Get payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    // Get payments by Order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable Long orderId) {
        List<Payment> payments = paymentService.getPaymentsByOrderId(orderId);
        return ResponseEntity.ok(payments);
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<Payment>> findAllPayments() {
        List<Payment> payments = paymentService.findAllPayments();
        return ResponseEntity.ok(payments);
    }

    // Update payment status
    @PutMapping("/update/{paymentId}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long paymentId,
            @RequestParam String paymentStatus) {
        Payment updatedPayment = paymentService.updatePayment(paymentId, paymentStatus);
        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }
}
