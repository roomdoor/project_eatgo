package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 =
                Restaurant.builder()
                        .id(100L)
                        .categoryId(1L)
                        .name("chicken")
                        .address("Seoul")
                        .build();


        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .categoryId(1L)
                .name("zzimdark")
                .address("Seoul")
                .build();

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(100L)).willReturn(Optional.of(restaurant1));
        given(restaurantRepository.findById(200L)).willReturn(Optional.of(restaurant2));

    }

    @DisplayName("1. getRestaurant Exist")
    @Test
    void test_1() {
        var restaurants = restaurantService.getRestaurants();
        var restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(100L));
    }

    @DisplayName("1. getRestaurant NotExist")
    @Test
    void test_1_1() {
        assertThrows(RestaurantNotFoundException.class,
                () -> restaurantService.getRestaurant(500L));
    }

    @DisplayName("2. getAllRestaurants")
    @Test
    void test_2() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), Is.is(100L));
        assertThat(restaurant.getName(), Is.is("chicken"));
        assertThat(restaurant.getAddress(), Is.is("Seoul"));
    }

    @DisplayName("3. getMenuItem")
    @Test
    void test_3() {
        Restaurant restaurant = restaurantService.getRestaurant(100L);
        assertThat(restaurant.getId(), is(100L));
    }

    @DisplayName("4. addRestaurant")
    @Test
    void test_4() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1000L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .categoryId(1L)
                .name("?????????")
                .address("?????????")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1000L));

    }

    @DisplayName("5. update")
    @Test
    void test_5() {
        Restaurant restaurant = Restaurant.builder()
                .id(100L)
                .categoryId(1L)
                .name("?????????")
                .address("??????")
                .build();

        given(restaurantRepository.findById(100L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(100L, "?????????", "?????????");

        assertThat(restaurant.getName(), Is.is("?????????"));
        assertThat(restaurant.getAddress(), Is.is("?????????"));
    }
}