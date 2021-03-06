package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/restaurants/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> reviewAdd(@PathVariable("restaurantId") Long restaurantId,
                                       @Valid @RequestBody Review resource) throws URISyntaxException {

        Review review = reviewService.addReview(restaurantId, resource);

        URI uri = new URI("/restaurants/" + restaurantId +
                "/reviews/" + review.getId());
        return ResponseEntity.created(uri).body("{}");
    }
}
