package com.example.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


class RestaurantTest {

    @Test
    public void creation() {
        Restaurant restaurant = new Restaurant(100L, "chicken", "");
        assertThat(restaurant.getName(), is("chicken"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant(100L, "chicken", "Seoul");
        assertThat(restaurant.getInformation(), is("chicken in Seoul"));
    }

}