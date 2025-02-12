package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.AddGameToWishListDTO;
import com.example.gamebazzar.model.DTO.CreateWishListDTO;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.WishListItem;

import java.util.List;

public interface WishListService {

    WishList createWishList(CreateWishListDTO createWishListDTO);

    WishList addGameToWishList(Long wishlistId, AddGameToWishListDTO request);

    WishList getWishListById(Long wishlistId);

    List<WishList> getAllWishListsForUser(Long userId);

    List<WishList> getWishListsByUser(User user);

    WishList updateWishList(Long wishlistId, CreateWishListDTO request);
    void deleteWishList(Long wishlistId);
    void clearWishList(Long wishlistId);
}
