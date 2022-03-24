package com.example.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class RestaurantTest {

    @Test
    public void creation() {
        Restaurant restaurant =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("")
                        .build();

        assertThat(restaurant.getName(), is("chicken"));
    }

    @Test
    public void information() {
        Restaurant restaurant =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .build();
        
        assertThat(restaurant.getInformation(), is("chicken in Seoul"));
    }

}