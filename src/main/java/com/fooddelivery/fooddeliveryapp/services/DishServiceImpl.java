package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Dish;
import com.fooddelivery.fooddeliveryapp.exceptions.DishNotFoundException;
import com.fooddelivery.fooddeliveryapp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    CartDishService cartDishService = new CartDishService();
    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public Dish updateDish(String name, Dish dishDetails) {
        Dish existingDish = dishRepository.findByName(name);
        if (existingDish != null) {
            existingDish.setName(dishDetails.getName());
            existingDish.setDescription(dishDetails.getDescription());
            existingDish.setPrice(dishDetails.getPrice());
            existingDish.setCategory(dishDetails.getCategory());
            existingDish.setRating(dishDetails.getRating());
            existingDish.setImage(dishDetails.getImage());
            existingDish.setQuantity(dishDetails.getQuantity());
            return dishRepository.save(existingDish);
        } else {
            throw new IllegalArgumentException("Dish not found");
        }
    }

    public void deleteDish(String name) {
        Dish dish = dishRepository.findByName(name);
        if (dish != null) {
            dishRepository.delete(dish);
        } else {
            throw new IllegalArgumentException("Dish not found");
        }
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
    public List<Dish> findByName(String name) {
        return dishRepository.findByNameContainingIgnoreCase(name);
    }

}
