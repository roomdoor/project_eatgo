package com.example.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository{

    private final List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public List<MenuItem> findAll() {
        return null;
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long id) {
        return menuItems;
    }
}
