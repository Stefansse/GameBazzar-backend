package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "_wishlistitems")
@Data
public class WishListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishListItemId;

    @ManyToOne
    @JsonBackReference
    private WishList wishList;

    @ManyToOne
    private Game game;


    @Column(name = "Quantity")
    private Integer quantity;


    public WishListItem(){

    }

    public WishListItem(WishList wishList, Game game, Integer quantity){
        this.wishList = wishList;
        this.game = game;
        this.quantity = quantity;

    }
}
