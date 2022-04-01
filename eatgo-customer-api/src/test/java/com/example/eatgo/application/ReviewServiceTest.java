package com.example.eatgo.application;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import com.example.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUP() {
        MockitoAnnotations.openMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @DisplayName("1. reviewAdd")
    @Test
    void test_1() {
        Review review = Review.builder()
                .id(1L)
                .name("sihwa")
                .score(5)
                .restaurantId(1L)
                .comment("good")
                .build();

        Review review1 = Review.builder()
                .id(2L)
                .name("sihwa")
                .score(5)
                .restaurantId(1L)
                .comment("good")
                .build();

        Review review2 = Review.builder()
                .id(3L)
                .name("sihwa")
                .score(5)
                .restaurantId(1L)
                .comment("good")
                .build();

        Review review3 = Review.builder()
                .id(3L)
                .remove(true)
                .build();

        reviewService.addReview(1L, review);
        reviewService.addReview(1L, review1);
        reviewService.addReview(1L, review2);
//        reviewService.addReview(review3);

        verify(reviewRepository, times(3)).save(any());
//        verify(reviewRepository, times(1)).deleteById(3L);
    }
}