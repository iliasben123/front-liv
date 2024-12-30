package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Dish;

import java.util.List;

public interface DishService {
    Dish addDish(Dish dish);

    Dish updateDish(String name, Dish dishDetails);

    void deleteDish(String name);
    List<Dish> findByName(String name);

    Iterable<Dish> getAllDishes(String filter, String sortBy, String category);
}
