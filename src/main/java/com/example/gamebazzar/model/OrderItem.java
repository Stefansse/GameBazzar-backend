package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne
    private Game game;

    @Column(nullable = false, name = "Quantity")
    private Integer quantity;

    @Column(nullable = false, name = "OrderItemPrice")
    private Double orderItemPrice;

    // Default constructor and other constructors
}

