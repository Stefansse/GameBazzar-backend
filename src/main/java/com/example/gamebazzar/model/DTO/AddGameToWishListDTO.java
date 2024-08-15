package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class AddGameToWishListDTO {
    private Long wishlistId;
    private Long gameId;
    private Integer quantity;
}
