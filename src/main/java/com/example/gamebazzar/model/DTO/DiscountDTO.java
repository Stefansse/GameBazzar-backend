package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountDTO {
    private Long id;
    private String code;
    private double percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long gameId;

}
