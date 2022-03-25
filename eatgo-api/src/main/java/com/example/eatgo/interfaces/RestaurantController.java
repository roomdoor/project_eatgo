package com.example.eatgo.interfaces;


import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.Restaurant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant info(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {
        Restaurant restaurant = restaurantService.addRestaurant(Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody Restaurant restaurant) {

        restaurantService.updateRestaurant(id, restaurant.getName(), restaurant.getAddress());
        return "{}";
    }
}
