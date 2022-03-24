package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.Restaurant;
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

    @DisplayName("1. list")
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(100L, "chicken", "Seoul"));
        restaurants.add(new Restaurant(200L, "zzimdark", "Seoul"));

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":100"))
                ).andExpect(content().string(
                        containsString("\"name\":\"chicken\""))
                ).andExpect(content().string(
                        containsString("\"id\":200"))
                ).andExpect(content().string(
                        containsString("\"name\":\"zzimdark\""))
                )
        ;

    }

    @DisplayName("2. store info")
    @Test
    void test_1() throws Exception {
        Restaurant restaurant1 = new Restaurant(100L, "chicken", "Seoul");
        restaurant1.addMenuItem(new MenuItem("fried"));
        Restaurant restaurant2 = new Restaurant(200L, "zzimdark", "Seoul");

        given(restaurantService.getRestaurant(100L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(200L)).willReturn(restaurant2);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":100"))
                ).andExpect(content().string(
                        containsString("\"name\":\"chicken\""))
                ).andExpect(content().string(
                        containsString("fried")
                ));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/200"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":200"))
                ).andExpect(content().string(
                        containsString("\"name\":\"zzimdark\""))
                );

    }

}