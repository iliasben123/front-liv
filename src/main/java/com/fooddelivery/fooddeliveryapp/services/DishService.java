package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Dish;

public interface DishService {
    Dish addDish(Dish dish);

    Dish updateDish(Long dishId, Dish dishDetails);

    void deleteDish(Long dishId);

    Iterable<Dish> getAllDishes(String filter, String sortBy, String category);
}
