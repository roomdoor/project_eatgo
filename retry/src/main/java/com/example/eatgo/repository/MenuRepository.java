package com.example.eatgo.repository;

import java.util.List;

public interface MenuRepository {
    public List<Menu> findAllByRestaurantId(Long restaurantId);
}
