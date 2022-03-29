package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReviewService reviewService;

    @DisplayName("1. reviewAdd")
    @Test
    void test_1() throws Exception {
        given(reviewService.addReview(any(), any())).willReturn(Review.builder()
                .id(1L)
                .name("sihwa")
                .score(5)
                .comment("good")
                .build());

        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"sihwa\",\"score\":5,\"comment\":\"good\"}")
                ).andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reviews/1"))
        ;

        verify(reviewService).addReview(any(), any());
    }

    @DisplayName("1. reviewAdd invalid")
    @Test
    void test_1_1() throws Exception {

        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
        ).andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(any(), any());
    }
}