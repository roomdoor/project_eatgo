package com.example.eatgo.interfaces;


import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.Restaurant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list(
            @RequestParam("region") String region,
            @RequestParam("categoryId") Long categoryId
    ) {
        return restaurantService.getRestaurants(region, categoryId);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }
}
