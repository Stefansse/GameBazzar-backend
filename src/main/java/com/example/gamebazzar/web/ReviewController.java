package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.ReviewDTO;
import com.example.gamebazzar.model.DTO.ReviewUpdateDTO;
import com.example.gamebazzar.model.Review;
import com.example.gamebazzar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO reviewRequest) {
        Review review = reviewService.createReview(
                reviewRequest.getGameId(),
                reviewRequest.getUserId(),
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );
        return ResponseEntity.ok(review);
    }

    // Retrieve a review by ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all reviews for a specific game
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Review>> getReviewsByGame(@PathVariable Long gameId) {
        List<Review> reviews = reviewService.getReviewsByGame(gameId);
        return ResponseEntity.ok(reviews);
    }

    // Retrieve all reviews by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        Review updatedReview = reviewService.updateReview(reviewId, reviewUpdateDTO.getRating(), reviewUpdateDTO.getComment());
        return ResponseEntity.ok(updatedReview);
    }

    // Delete a review
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }
}
