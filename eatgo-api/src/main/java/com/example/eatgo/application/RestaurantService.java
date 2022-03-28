package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
import com.example.eatgo.domain.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }


    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void updateRestaurant(Long id, String name, String address) {
        restaurantRepository.findById(id).orElse(null)
                .updateRestaurant(name, address);
    }
}
