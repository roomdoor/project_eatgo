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
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = new Restaurant(1000L, name, address);
        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurant/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
}
