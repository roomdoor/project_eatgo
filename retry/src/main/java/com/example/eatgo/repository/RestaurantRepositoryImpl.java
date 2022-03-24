package com.example.eatgo.repository;

import com.example.eatgo.domain.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        restaurants.add(new Restaurant(100L, "chicken", "Seoul"));
        restaurants.add(new Restaurant(200L, "zzimdark", "Seoul"));
//        restaurants.stream().filter(r -> r.getId().equals(100L)).
//                findFirst().get().addMenu(new Menu("fried"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream().
                filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
