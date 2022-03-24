package com.example.eatgo.service;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.repository.MenuRepository;
import com.example.eatgo.repository.MenuRepositoryImpl;
import com.example.eatgo.repository.RestaurantRepository;
import com.example.eatgo.repository.RestaurantRepositoryImpl;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Before
    public void setUp() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
        MenuRepository menuRepository = new MenuRepositoryImpl();

        restaurantService = new RestaurantService(restaurantRepository, menuRepository);
    }


    @DisplayName("1. findId")
    @Test
    void test_1() {
        setUp();
        Restaurant restaurant = restaurantService.findRestaurant(100L);

        assertThat(restaurant.getId(), Is.is(100L));
        assertThat(restaurant.getName(), Is.is("chicken"));
        assertThat(restaurant.getAddress(), Is.is("Seoul"));
        assertThat(restaurant.getMenus().get(0).getMenuName(), Is.is("fried"));
    }

    @DisplayName("2. allRestaurants")
    @Test
    void test_2() {
        setUp();
        List<Restaurant> restaurants = restaurantService.allRestaurants();
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), Is.is(100L));
        assertThat(restaurant.getName(), Is.is("chicken"));
        assertThat(restaurant.getAddress(), Is.is("Seoul"));
        assertThat(restaurant.getMenus().get(0).getMenuName(), Is.is("fried"));

    }

}