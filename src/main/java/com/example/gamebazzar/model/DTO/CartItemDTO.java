package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long cartItemId;
    private GameDTO game;
    private Integer quantity;

}