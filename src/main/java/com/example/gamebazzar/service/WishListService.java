package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.AddGameToWishListDTO;
import com.example.gamebazzar.model.DTO.CreateWishListDTO;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.WishListItem;

import java.util.List;

public interface WishListService {

    // Create a new wishlist for a user
    WishList createWishList(CreateWishListDTO createWishListDTO);

    // Add a game to a wishlist
    WishList addGameToWishList(Long wishlistId, AddGameToWishListDTO request);

    // Retrieve a wishlist by ID
    WishList getWishListById(Long wishlistId);

    // Retrieve all wishlists for a user
    List<WishList> getAllWishListsForUser(Long userId);

    List<WishList> getWishListsByUser(User user);

    WishList updateWishList(Long wishlistId, CreateWishListDTO request);
    void deleteWishList(Long wishlistId);
    void clearWishList(Long wishlistId);
}
