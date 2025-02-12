package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.ReviewDTO;
import com.example.gamebazzar.model.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReviewService {


    Review createReview(Long gameId, Long userId, Integer rating, String comment);


    Optional<Review> getReviewById(Long reviewId);


    List<Map<String, Object>> getReviewsByGame(Long gameId);


    List<Review> getReviewsByUser(Long userId);


    Review updateReview(Long reviewId, Integer rating, String comment);


    void deleteReview(Long reviewId);
}
