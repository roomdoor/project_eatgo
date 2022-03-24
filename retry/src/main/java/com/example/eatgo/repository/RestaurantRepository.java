package com.example.eatgo.repository;

import com.example.eatgo.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    public List<Restaurant> findAll();

    public Restaurant findById(Long id);

}
