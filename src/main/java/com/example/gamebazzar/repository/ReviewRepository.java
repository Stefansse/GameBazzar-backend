package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Review;
import com.example.gamebazzar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByGame(Game game);
    List<Review> findByUser(User user);
}
