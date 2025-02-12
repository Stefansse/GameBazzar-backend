package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long cartId;
    private UserDTO user;
    private List<CartItemDTO> cartItems;


}