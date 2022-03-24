package com.example.eatgo.service;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.repository.Menu;
import com.example.eatgo.repository.MenuRepository;
import com.example.eatgo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

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
        Restaurant restaurant = restaurantRepository.findById(id);

        List<Menu> menus = menuRepository.findAllByRestaurantId(id);
        restaurant.setMenus(menus);

        return restaurant;
    }

    public List<Restaurant> allRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        for (Restaurant r : restaurants) {
            List<Menu> menus = menuRepository.findAllByRestaurantId(r.getId());
            r.setMenus(menus);
        }

        return restaurants;
    }
}
