package com.example.eatgo.application;

import com.example.eatgo.domain.Review;
import com.example.eatgo.domain.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review addReview(Long restaurantId, Review review) {
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }

}
