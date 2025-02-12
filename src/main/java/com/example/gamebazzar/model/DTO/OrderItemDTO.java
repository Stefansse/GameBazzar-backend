package com.example.gamebazzar.model.DTO;


import lombok.Data;

@Data
public class OrderItemDTO {
    private Long orderItemId;
    private GameDTO game;
    private Integer quantity;
    private Double orderItemPrice;


}
