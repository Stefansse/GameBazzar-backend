package com.example.gamebazzar.model.Cart;

import com.example.gamebazzar.model.Game;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne
    private Game game;

    @ManyToOne
    @JsonBackReference
    private Cart cart;

    private int quantity;

    public CartItem() {
    }

    public CartItem(Game game, int quantity) {
        this.game = game;
        this.quantity = quantity;
    }
}
