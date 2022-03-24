package com.example.eatgo.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuRepositoryImpl implements MenuRepository {
    private final List<Menu> menus = new ArrayList<>();

    public MenuRepositoryImpl() {
        menus.add(new Menu("fried"));
    }

    @Override
    public List<Menu> findAllByRestaurantId(Long restaurantId) {
        return menus;
    }
}
