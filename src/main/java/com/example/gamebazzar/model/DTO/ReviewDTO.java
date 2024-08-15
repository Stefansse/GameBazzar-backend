package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long gameId;
    private Long userId;
    private Integer rating;
    private String comment;
}

