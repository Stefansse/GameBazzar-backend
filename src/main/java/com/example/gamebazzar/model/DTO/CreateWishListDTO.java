package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateWishListDTO {
    private Long userId;
    private LocalDate creationDate;



}