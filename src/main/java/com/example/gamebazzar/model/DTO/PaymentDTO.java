package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {

    private Long orderId;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDate paymentDate; // Optional: if you want to set the payment date in the request
}