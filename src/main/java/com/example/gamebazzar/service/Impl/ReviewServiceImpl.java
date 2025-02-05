package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.Review;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.repository.ReviewRepository;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.repository.UserRepository;
import com.example.gamebazzar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new review
    public Review createReview(Long gameId, Long userId, Integer rating, String comment) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review(game, user, rating, comment, LocalDate.now());
        return reviewRepository.save(review);
    }

    // Retrieve a review by ID
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // Retrieve all reviews for a specific game
    public List<Map<String, Object>> getReviewsByGame(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Fetch reviews for the game
        List<Review> reviews = reviewRepository.findByGame(game);

        // Prepare a list of response maps to include userId with other review details
        List<Map<String, Object>> response = new ArrayList<>();

        for (Review review : reviews) {
            Map<String, Object> reviewMap = new HashMap<>();
            reviewMap.put("reviewId", review.getReviewId());
            reviewMap.put("rating", review.getRating());
            reviewMap.put("comment", review.getComment());
            reviewMap.put("reviewDate", review.getReviewDate());

            // Accessing the userId from the associated User entity
            if (review.getUser() != null) {
                reviewMap.put("userId", review.getUser().getId());
            } else {
                reviewMap.put("userId", null);  // If user is null, handle accordingly
            }

            response.add(reviewMap);
        }

        return response;
    }


    // Retrieve all reviews by a specific user
    public List<Review> getReviewsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return reviewRepository.findByUser(user);
    }

    // Update a review
    public Review updateReview(Long reviewId, Integer rating, String comment) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    // Delete a review
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
