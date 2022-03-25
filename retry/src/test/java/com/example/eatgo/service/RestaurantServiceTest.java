package com.example.eatgo.service;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.repository.MenuRepository;
import com.example.eatgo.repository.RestaurantRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {


    private RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;
    @Mock
    MenuRepository menuRepository;

    public void setUp() {
        mockGiven();

        restaurantService = new RestaurantService(restaurantRepository, menuRepository);
    }

    private void mockGiven() {
        MockitoAnnotations.openMocks(this);

        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = Restaurant.builder()
                .id(100L)
                .name("chicken")
                .address("Seoul")
                .menus(List.of(new Menu("fried")))
                .build();

        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .name("zzimdark")
                .address("Seoul")
                .build();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        given(restaurantRepository.findById(100L)).willReturn(Optional.ofNullable(restaurant1));
        given(restaurantRepository.findById(200L)).willReturn(Optional.ofNullable(restaurant2));
        given(restaurantRepository.findAll()).willReturn(restaurants);

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

    @DisplayName("3. addRestaurant")
    @Test
    void test_3() {
        setUp();
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1000L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("버거킹")
                .address("등촌점")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId(), is(1000L));
    }

    @DisplayName("4. update")
    @Test
    void test_4() {
        setUp();
        given(restaurantRepository.findById(100L)).willReturn(Optional.ofNullable(Restaurant.builder()
                .id(100L)
                .name("eee")
                .address("eee")
                .build()));

        Restaurant restaurant = restaurantService.updateRestaurant(100L, "eee", "eee");
        assertThat(restaurant.getName(), Is.is("eee"));
        assertThat(restaurant.getAddress(), Is.is("eee"));

    }

}