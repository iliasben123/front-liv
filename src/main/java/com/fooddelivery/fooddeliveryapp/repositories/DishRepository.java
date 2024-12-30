package com.fooddelivery.fooddeliveryapp.repositories;

import com.fooddelivery.fooddeliveryapp.entities.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
    Iterable<Dish> findByCategory(String category);
    List<Dish> findByNameContainingIgnoreCase(String filter);

    List<Dish> findAllByOrderByNameAsc();
    Dish findByName(String name);
}
