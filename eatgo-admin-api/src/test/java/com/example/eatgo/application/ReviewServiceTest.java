package com.example.eatgo.application;

import com.example.eatgo.domain.Review;
import com.example.eatgo.domain.ReviewRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

    @DisplayName("1. list")
    @Test
    public void test_1(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder().comment("good").build());
        given(reviewRepository.findAll()).willReturn(reviews);

        assertThat(reviewService.getReviews().get(0).getComment(), Is.is("good"));

    }

}