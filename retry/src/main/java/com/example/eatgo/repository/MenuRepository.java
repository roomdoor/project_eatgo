package com.example.eatgo.repository;

import com.example.eatgo.domain.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu, Long> {
    public List<Menu> findAllByRestaurantId(Long restaurantId);

    public void deleteById(Long restaurantId);
}
