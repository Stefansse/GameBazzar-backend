package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListItemRepository extends JpaRepository<WishListItem, Long> {
    List<WishListItem> findByWishList(WishList wishlist);
    List<WishListItem> findByGame(Game game);

    WishListItem findByWishListAndGame(WishList wishList, Game game);
}
