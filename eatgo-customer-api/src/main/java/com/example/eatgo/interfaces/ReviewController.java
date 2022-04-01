package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
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
