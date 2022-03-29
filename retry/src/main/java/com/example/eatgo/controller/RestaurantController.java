package com.example.eatgo.controller;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return restaurantService.allRestaurants();
    }

    @GetMapping("restaurants/{id}")
    public Restaurant restaurant(@PathVariable("id") Long id) {

        return restaurantService.findRestaurant(id);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> addRestaurant(@Valid @RequestBody Restaurant resource)
            throws URISyntaxException {

        Restaurant restaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                        .name(resource.getName())
                        .address(resource.getAddress())
                        .build());

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String updateRestaurant(@Valid @PathVariable("id") Long id,
                                   @RequestBody Restaurant resource) {
        restaurantService.updateRestaurant(id, resource.getName(), resource.getAddress());
        return "{}";
    }
}
