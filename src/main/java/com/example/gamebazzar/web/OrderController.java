package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.PaymentResponse;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.service.OrderService;
import com.example.gamebazzar.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")

public class OrderController {

    private final OrderService orderService;

    private final PaymentService paymentService;

    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    // Create a new order
    @PostMapping("/add")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody Order order) {
        //Order createdOrder = orderService.addOrder(order);
        PaymentResponse res = paymentService.createPaymentService(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


//    @PostMapping("/add")
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        Order createdOrder = orderService.addOrder(order);
//        return ResponseEntity.ok(createdOrder);
//    }

    // Get an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.findOrderById(orderId);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Delete an order by ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }



}
