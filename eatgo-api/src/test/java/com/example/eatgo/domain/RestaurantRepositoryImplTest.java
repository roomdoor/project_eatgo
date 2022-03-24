package com.example.eatgo.domain;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class RestaurantRepositoryImplTest {

    @DisplayName("1. $END#")
    @Test
    void test_1() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

        int oldCount = restaurantRepository.findAll().size();

        Restaurant restaurant = new Restaurant("버거킹", "등촌점");
        restaurantRepository.save(restaurant);

        int newCount = restaurantRepository.findAll().size();

        assertThat(newCount - oldCount, Is.is(1));
        assertThat(restaurant.getId(),Is.is(1000L));
    }

}