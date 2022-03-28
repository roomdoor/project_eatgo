package com.example.eatgo.service;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
import com.example.eatgo.repository.MenuRepository;
import com.example.eatgo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }


    public Restaurant findRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public List<Restaurant> allRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.setName(name);
        restaurant.setAddress(address);

        return restaurant;
    }
}
