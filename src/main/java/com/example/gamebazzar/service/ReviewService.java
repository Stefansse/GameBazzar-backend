package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.ReviewDTO;
import com.example.gamebazzar.model.Review;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReviewService {

    // Create a new review
    Review createReview(Long gameId, Long userId, Integer rating, String comment);

    // Retrieve a review by ID
    Optional<Review> getReviewById(Long reviewId);

    // Retrieve all reviews for a specific game
    List<Map<String, Object>> getReviewsByGame(Long gameId);

    // Retrieve all reviews by a specific user
    List<Review> getReviewsByUser(Long userId);

    // Update a review
    Review updateReview(Long reviewId, Integer rating, String comment);

    // Delete a review
    void deleteReview(Long reviewId);
}
