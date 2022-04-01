package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
import com.example.eatgo.domain.Review;
import com.example.eatgo.interfaces.RestaurantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @DisplayName("1. list")
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .build();

        restaurant.setMenuItems(List.of(MenuItem.builder().name("fried").build()));
        restaurant.setReviews(List.of(Review.builder().name("sihwa").score(5).comment("good").build()));

        restaurants.add(restaurant);

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":100"))
                ).andExpect(content().string(
                        containsString("\"name\":\"chicken\""))
                ).andExpect(content().string(
                        containsString("sihwa"))
                )
        ;
    }

    @DisplayName("2. store info Exist")
    @Test
    public void test_1() throws Exception {
        Restaurant restaurant1 =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .menuItems(List.of(MenuItem.builder().name("fried").build()))
                        .build();

        restaurant1.setMenuItems(List.of(MenuItem.builder().name("fried").build()));
        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .name("zzimdark")
                .address("Seoul")
                .build();

        Review review = Review.builder().name("sihwa").score(5).comment("good").build();
        restaurant1.setReviews(List.of(review));

        given(restaurantService.getRestaurant(100L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(200L)).willReturn(restaurant2);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":100"))
                ).andExpect(content().string(
                        containsString("\"name\":\"chicken\""))
                ).andExpect(content().string(
                        containsString("fried"))
                ).andExpect(content().string(
                        containsString("sihwa"))
                ).andExpect(content().string(
                        containsString("good"))
                )
        ;

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/200"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":200"))
                ).andExpect(content().string(
                        containsString("\"name\":\"zzimdark\""))
                );

    }

    @DisplayName("2. store info NotExist")
    @Test
    public void test_1_2() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}