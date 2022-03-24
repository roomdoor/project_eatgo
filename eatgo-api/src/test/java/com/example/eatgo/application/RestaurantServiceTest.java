package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;


public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant(100L, "chicken", "Seoul");
        restaurant1.getMenuItems().add(new MenuItem("fried"));
        Restaurant restaurant2 = new Restaurant(200L, "zzimdark", "Seoul");
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(100L)).willReturn(restaurant1);
        given(restaurantRepository.findById(200L)).willReturn(restaurant2);
    }

    @DisplayName("1. getRestaurant")
    @Test
    void test_1() {
        setUp();
        Restaurant restaurant = restaurantService.getRestaurant(100L);

        assertThat(restaurant.getId(), is(100L));
    }

    @DisplayName("2. getRestaurants")
    @Test
    void test_2() {
        setUp();
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), Is.is(100L));
        assertThat(restaurant.getName(), Is.is("chicken"));
        assertThat(restaurant.getAddress(), Is.is("Seoul"));
    }

    @DisplayName("3. getMenuItem")
    @Test
    void test_3() {
        setUp();
        Restaurant restaurant = restaurantService.getRestaurant(100L);
        assertThat(restaurant.getId(), is(100L));

        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("fried"));


    }

}