package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Cart;
import com.fooddelivery.fooddeliveryapp.entities.Dish;
import com.fooddelivery.fooddeliveryapp.entities.Status;
import com.fooddelivery.fooddeliveryapp.repositories.CartRepository;
import com.fooddelivery.fooddeliveryapp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DishRepository dishRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, DishRepository dishRepository) {
        this.cartRepository = cartRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Cart getCartForUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart addDishToCart(Long userId, Long dishId) {
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isPresent()) {
            Cart cart = getCartForUser(userId);
            cart.getDishes().add(dish.get());
            cart.setTotalPrice(cart.getTotalPrice() + dish.get().getPrice());
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Cart removeDishFromCart(Long userId, Long dishId) {
        Cart cart = getCartForUser(userId);
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isPresent()) {
            cart.getDishes().remove(dish.get());
            cart.setTotalPrice(cart.getTotalPrice() - dish.get().getPrice());
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Cart updateCart(Long userId, Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void checkoutCart(Long userId) {
        Cart cart = getCartForUser(userId);
        cart.setStatus(Status.LIVRE);
        cartRepository.save(cart);
    }
}
