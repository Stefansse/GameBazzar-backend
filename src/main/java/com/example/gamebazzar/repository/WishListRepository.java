package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUser(User user);
}
