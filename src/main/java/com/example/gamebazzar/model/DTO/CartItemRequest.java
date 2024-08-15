package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long gameId;
    private int quantity;
}
