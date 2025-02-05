package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.ReviewDTO;
import com.example.gamebazzar.model.DTO.ReviewUpdateDTO;
import com.example.gamebazzar.model.Review;
import com.example.gamebazzar.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")

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
    public ResponseEntity<List<Map<String, Object>>> getReviewsByGame(@PathVariable Long gameId) {
        List<Map<String, Object>> reviews = reviewService.getReviewsByGame(gameId);
        return ResponseEntity.ok(reviews);
    }

    // Retrieve all reviews by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUser(@PathVariable Long userId) {
        List<Review> reviews = reviewService.getReviewsByUser(userId);

        List<ReviewDTO> reviewDTOs = reviews.stream().map(review ->
                new ReviewDTO(
                        review.getGame().getGameId(),
                        review.getGame().getTitle(),
                        review.getGame().getImageUrl(),  // Assuming `Game` has an image URL
                        review.getUser().getId(),
                        review.getRating(),
                        review.getComment(),
                        review.getReviewDate()
                )
        ).collect(Collectors.toList());

        return ResponseEntity.ok(reviewDTOs);
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
