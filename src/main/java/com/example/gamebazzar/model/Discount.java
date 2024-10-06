package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude = "game") // Exclude the game to avoid circular references
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;  // Discount code

    @Column(nullable = false)
    private double percentage;  // Discount percentage, e.g., 10 for 10%

    @Column(nullable = false)
    private LocalDate startDate;  // Start date of the discount

    @Column(nullable = false)
    private LocalDate endDate;  // End date of the discount

    @ManyToOne
    @JsonIgnore
    private Game game;


    public Discount() {
    }

    public Discount(String code, double percentage, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}