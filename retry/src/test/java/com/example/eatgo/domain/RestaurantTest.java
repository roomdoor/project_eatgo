package com.example.eatgo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestaurantTest {

    @DisplayName("1. creation")
    @Test
    void test_1() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(100L);
        restaurant.setName("chicken");
        restaurant.setAddress("Seoul");

        assertThat(restaurant.getId(), is(100L));
        assertThat(restaurant.getName(), is("chicken"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @DisplayName("2. info")
    @Test
    void test_2() {
        Restaurant restaurant = Restaurant.builder()
                .id(100L)
                .name("chicken")
                .address("Seoul")
                .build();
        assertThat(restaurant.getInfo(), is("chicken in Seoul"));
    }

}