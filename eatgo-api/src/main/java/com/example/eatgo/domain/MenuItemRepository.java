package com.example.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {
    List<MenuItem> findAll();

    List<MenuItem> findAllByRestaurantId(Long id);
}
