package com.fooddelivery.fooddeliveryapp.repositories;

import com.fooddelivery.fooddeliveryapp.entities.CartDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDishRepository extends JpaRepository<CartDish, Long> {
    void deleteByDishId(Long dishId);  // Cette méthode supprimera tous les enregistrements où `dish_id` correspond à l'ID du plat.
}
