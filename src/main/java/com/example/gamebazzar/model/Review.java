package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false, name = "Rating")
    private Integer rating;

    @Column(columnDefinition = "TEXT", name = "Comment")
    private String comment;

    @Column(nullable = false, name = "ReviewDate")
    private LocalDate reviewDate;

    @ManyToOne
    @JsonBackReference
    private User user;


    @ManyToOne
    @JsonIgnore
    private Game game;

    public Review(){

    }

    public Review(Game game, User user, Integer rating, String comment, LocalDate reviewDate) {
        this.game = game;
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
}
