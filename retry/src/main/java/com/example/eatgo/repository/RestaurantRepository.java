package com.example.eatgo.repository;

import com.example.eatgo.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);
}
