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

    @DisplayName("1. list")
    @Test
    public void list() throws Exception {
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
        Restaurant restaurant1 =
                Restaurant.builder()
                        .id(100L)
                        .name("chicken")
                        .address("Seoul")
                        .menuItems(List.of(new MenuItem("fried")))
                        .build();

        restaurant1.setMenuItems(List.of(new MenuItem("fried")));
        Restaurant restaurant2 = Restaurant.builder()
                .id(200L)
                .name("zzimdark")
                .address("Seoul")
                .build();

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


    @DisplayName("3. create")
    @Test
    void test_3() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1000L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"버거킹\",\"address\":\"등촌점\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/null"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @DisplayName("4. update")
    @Test
    void test_4() throws Exception {

        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"다코기\",\"address\":\"등촌동\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(100L, "다코기", "등촌동");

    }
}