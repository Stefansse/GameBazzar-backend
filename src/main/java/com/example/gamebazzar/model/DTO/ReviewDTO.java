package com.example.gamebazzar.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {
    private Long gameId;
    private String gameName;
    private String gameImageUrl; // Optional if you store images
    private Long userId;
    private Integer rating;
    private String comment;
    private LocalDate reviewDate;


    public ReviewDTO(Long gameId, String gameName, String gameImageUrl, Long userId, Integer rating, String comment, LocalDate reviewDate) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameImageUrl = gameImageUrl;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}

