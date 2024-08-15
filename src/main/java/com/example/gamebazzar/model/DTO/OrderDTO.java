package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private Double totalAmount;
    private String status;
    private Integer orderSize;
    private String currency;
    private UserDTO user;
    private List<OrderItemDTO> orderItems;

    // Getters and setters
}
