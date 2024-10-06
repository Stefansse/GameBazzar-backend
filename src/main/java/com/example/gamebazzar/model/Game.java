package com.example.gamebazzar.model;

import com.example.gamebazzar.model.enumerations.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "discount")
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "Text", name = "Description")
    private String description;

    @Column(nullable = false, name = "ReleaseDate")
    private LocalDate releaseDate;
    @Column(nullable = false, name = "Price")
    private Double price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "Rating")
    private double rating;

    public Game() {

    }

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<WishListItem> wishlistItems;

    @OneToOne
    @JsonIgnore
    private Discount discount;

    public Game(String title, String description, LocalDate releaseDate, Double price, Genre genre, String publisher, Double rating) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.genre = genre;
        this.publisher = publisher;
        this.rating = rating;
        this.orderItems = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.wishlistItems = new ArrayList<>();

    }
}
