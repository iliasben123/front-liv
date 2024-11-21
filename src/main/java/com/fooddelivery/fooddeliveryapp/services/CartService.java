package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Cart;
import com.fooddelivery.fooddeliveryapp.entities.Dish;

public interface CartService {
    Cart getCartForUser(Long userId);

    Cart addDishToCart(Long userId, Long dishId);

    Cart removeDishFromCart(Long userId, Long dishId);

    Cart updateCart(Long userId, Cart cart);

    void checkoutCart(Long userId);
}
