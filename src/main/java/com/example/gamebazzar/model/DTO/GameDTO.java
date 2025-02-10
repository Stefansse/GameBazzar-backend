package com.example.gamebazzar.model.DTO;

import com.example.gamebazzar.model.Discount;
import com.example.gamebazzar.model.enumerations.Genre;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GameDTO {
    private Long gameId;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Double price;
    private Genre genre;
    private String publisher;
    private Double rating;
    private Discount discount;
    // Exclude other fields to avoid recursion
}
