package com.example.eatgo.controller;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.repository.Menu;
import com.example.eatgo.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @DisplayName("1. /info")
    @Test
    void test_1() throws Exception {
        Restaurant restaurant1 = new Restaurant(100L, "chicken", "Seoul");
        restaurant1.getMenus().add(new Menu("fried"));
        Restaurant restaurant2 = new Restaurant(200L, "zzimdark", "Seoul");
        given(restaurantService.findRestaurant(100L)).willReturn(restaurant1);
        given(restaurantService.findRestaurant(200L)).willReturn(restaurant2);

        mvc.perform(MockMvcRequestBuilders.get("/restaurantInfo/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"chicken\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("fried")))
        ;

        mvc.perform(MockMvcRequestBuilders.get("/restaurantInfo/200"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":200")))
                .andExpect(content().string(containsString("\"name\":\"zzimdark\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
        ;

    }

    @DisplayName("2. /restaurants")
    @Test
    void test_2() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(100L, "chicken", "Seoul"));

        given(restaurantService.allRestaurants()).willReturn(restaurants);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"chicken\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
        ;
    }
}