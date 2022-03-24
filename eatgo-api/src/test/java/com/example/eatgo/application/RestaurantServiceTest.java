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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        Restaurant restaurant1 =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .build();

        restaurant1.setMenuItems(List.of(new MenuItem("fried")));
        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .name("zzimdark")
                .address("Seoul")
                .build();

        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(100L)).willReturn(Optional.of(restaurant1));
        given(restaurantRepository.findById(200L)).willReturn(Optional.of(restaurant2));
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

    @DisplayName("4. addRestaurant")
    @Test
    void test_4() {
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

    @DisplayName("5. update")
    @Test
    void test_5() {
        setUp();
        Restaurant restaurant = Restaurant.builder()
                .id(100L)
                .name("치킨집")
                .address("서울")
                .build();

        given(restaurantRepository.findById(100L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(100L, "다코기", "등촌동");

        assertThat(restaurant.getName(), Is.is("다코기"));
        assertThat(restaurant.getAddress(), Is.is("등촌동"));
    }
}