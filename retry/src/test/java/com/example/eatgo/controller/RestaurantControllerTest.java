package com.example.eatgo.controller;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.service.RestaurantService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @DisplayName("3. addRestaurants")
    @Test
    void test_3() throws Exception {
        given(restaurantService.addRestaurant(any())).willReturn(Restaurant.builder()
                .id(100L)
                .name("버거킹")
                .address("등촌점")
                .build());

        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"버거킹\", \"address\":\"등촌점\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/100"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(Restaurant.builder()
                .name("버거킹")
                .address("등촌점")
                .build());
    }


    @DisplayName("5 . update")
    @Test
    void test_5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"다코기\",\"address\":\"등촌점\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(100L, "다코기", "등촌점");
    }
}