package com.example.eatgo.controller;

import com.example.eatgo.domain.Menu;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Restaurant restaurant1 = Restaurant.builder()
                .id(100L)
                .name("chicken")
                .address("Seoul")
                .build();
        restaurant1.setMenus(List.of(Menu.builder().menuName("fried").build()));
        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .name("zzimdark")
                .address("Seoul")
                .build();
        given(restaurantService.findRestaurant(100L)).willReturn(restaurant1);
        given(restaurantService.findRestaurant(200L)).willReturn(restaurant2);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"chicken\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
                .andExpect(content().string(containsString("fried")))
        ;

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/200"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":200")))
                .andExpect(content().string(containsString("\"name\":\"zzimdark\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
        ;

    }

    @DisplayName("1. /info not exist")
    @Test
    void test_1_1() throws Exception {
        given(restaurantService.findRestaurant(100L)).willThrow(RestaurantNotFoundException.class);

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findRestaurant(100L));
    }


    @DisplayName("2. /restaurants")
    @Test
    void test_2() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(100L)
                .name("chicken")
                .address("Seoul")
                .build());

        given(restaurantService.allRestaurants()).willReturn(restaurants);

        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"chicken\"")))
                .andExpect(content().string(containsString("\"address\":\"Seoul\"")))
        ;
    }

    @DisplayName("3. addRestaurants NotExist")
    @Test
    void test_3() throws Exception {
        given(restaurantService.addRestaurant(any())).willReturn(Restaurant.builder()
                .id(100L)
                .name("?????????")
                .address("?????????")
                .build());

        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"?????????\", \"address\":\"?????????\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/100"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(Restaurant.builder()
                .name("?????????")
                .address("?????????")
                .build());
    }

    @DisplayName("5 . update")
    @Test
    void test_5() throws Exception {
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"?????????\",\"address\":\"?????????\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(100L, "?????????", "?????????");
    }
}