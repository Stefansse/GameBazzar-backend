package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.DTO.AddGameToWishListDTO;
import com.example.gamebazzar.model.DTO.CreateWishListDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.WishListItem;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.repository.UserRepository;
import com.example.gamebazzar.repository.WishListRepository;
import com.example.gamebazzar.repository.WishListItemRepository;
import com.example.gamebazzar.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private WishListItemRepository wishListItemRepository;

    public WishList createWishList(CreateWishListDTO createWishListDTO) {
        User user = userRepository.findById(createWishListDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setCreationDate(createWishListDTO.getCreationDate() != null ? createWishListDTO.getCreationDate() : LocalDate.now());

        return wishListRepository.save(wishList);
    }

    @Override
    public WishList addGameToWishList(Long wishlistId, AddGameToWishListDTO request) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));

        Game game = gameRepository.findById(request.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));


        WishListItem existingItem = wishListItemRepository.findByWishListAndGame(wishList, game);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
            wishListItemRepository.save(existingItem);
        } else {
            WishListItem wishListItem = new WishListItem(wishList, game, request.getQuantity());
            wishListItemRepository.save(wishListItem);
        }

        return wishList;
    }


    public WishList getWishListById(Long wishlistId) {
        return wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
    }


    public List<WishList> getAllWishListsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return wishListRepository.findByUser(user);
    }

    @Override
    public List<WishList> getWishListsByUser(User user) {
        return wishListRepository.findByUser(user);
    }

    @Override
    public WishList updateWishList(Long wishlistId, CreateWishListDTO request) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));



        return wishListRepository.save(wishList);
    }

    @Override
    public void deleteWishList(Long wishlistId) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));
        wishListRepository.delete(wishList);
    }

    @Override
    public void clearWishList(Long wishlistId) {
        WishList wishList = wishListRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("WishList not found"));

        wishListItemRepository.deleteAll(wishList.getWishlistItems());

        wishList.getWishlistItems().clear();

        wishListRepository.save(wishList);
    }
}
