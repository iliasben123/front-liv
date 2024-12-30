package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.repositories.CartDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDishService {

    @Autowired
    private CartDishRepository cartDishRepository;  // Injecte le repository CartDish

    /**
     * Supprimer tous les plats d'un panier en fonction de l'ID du plat.
     * Cette méthode supprime toutes les entrées correspondantes dans la table cart_dishes
     * qui ont ce plat spécifique dans les paniers.
     *
     * @param dishId L'ID du plat à supprimer des paniers
     */
    public void removeCartDishByDishId(Long dishId) {
        cartDishRepository.deleteByDishId(dishId);  // Supprime tous les CartDish liés au plat
    }

    // Autres méthodes de gestion des plats dans le panier (par exemple, ajouter un plat, mettre à jour la quantité, etc.)
}
