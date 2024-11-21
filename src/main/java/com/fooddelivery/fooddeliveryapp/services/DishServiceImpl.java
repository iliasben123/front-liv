package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Dish;
import com.fooddelivery.fooddeliveryapp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Long dishId, Dish dishDetails) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new IllegalArgumentException("Dish not found"));
        dish.setName(dishDetails.getName());
        dish.setPrice(dishDetails.getPrice());
        dish.setDescription(dishDetails.getDescription());
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long dishId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new IllegalArgumentException("Dish not found"));
        dishRepository.delete(dish);
    }

    @Override
    public Iterable<Dish> getAllDishes(String filter, String sortBy , String category) {
        if (category != null && !category.isEmpty()) {
            return dishRepository.findByCategory(category);  // Ajoutez cette méthode dans le repository
        }
        if (filter != null && !filter.isEmpty()) {
            return dishRepository.findByNameContainingIgnoreCase(filter);
        }
        if (sortBy != null && !sortBy.isEmpty()) {
            return dishRepository.findAllByOrderByNameAsc(); // Exemple de tri par nom
        }
        return dishRepository.findAll();
    }
}
