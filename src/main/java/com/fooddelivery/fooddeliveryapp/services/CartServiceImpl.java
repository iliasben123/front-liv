package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.Cart;
import com.fooddelivery.fooddeliveryapp.entities.Dish;
import com.fooddelivery.fooddeliveryapp.entities.Status;
import com.fooddelivery.fooddeliveryapp.repositories.CartRepository;
import com.fooddelivery.fooddeliveryapp.repositories.DishRepository;
import com.fooddelivery.fooddeliveryapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;  // Déclaration du UserRepository

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, DishRepository dishRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;  // Injection du UserRepository
    }

    @Override
    public Cart getCartForUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart addDishToCart(Long userId, Long dishId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro.");
        }

        // Récupérer le plat
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new RuntimeException("Plat introuvable avec l'ID: " + dishId));

        // Gérer le panier et l'ajout de plats
        Cart cart = getCartForUser(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID: " + userId)));
            cart.setTotalPrice(0.0);
            cart.setStatus(Status.LIVRE);
        }

        // Ajouter ou mettre à jour le plat dans le panier
        boolean dishExists = cart.getDishes().stream().anyMatch(cartDish -> cartDish.getId().equals(dishId));

        if (dishExists) {
            cart.getDishes().forEach(cartDish -> {
                if (cartDish.getId().equals(dishId)) {
                    cartDish.setQuantity(cartDish.getQuantity() + quantity);
                }
            });
        } else {
            dish.setQuantity(quantity);
            cart.getDishes().add(dish);
        }

        cart.setTotalPrice(cart.getTotalPrice() + (dish.getPrice() * quantity));
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeDishFromCart(Long userId, Long dishId) {
        Cart cart = getCartForUser(userId);
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isPresent()) {
            cart.getDishes().remove(dish.get());  // Suppression du plat du panier
            cart.setTotalPrice(cart.getTotalPrice() - dish.get().getPrice());  // Mise à jour du prix total
            return cartRepository.save(cart);  // Sauvegarde du panier
        }
        return null;
    }

    @Override
    public Cart updateCart(Long userId, Cart cart) {
        return cartRepository.save(cart);  // Sauvegarde de la mise à jour du panier
    }

    @Override
    public void checkoutCart(Long userId) {
        Cart cart = getCartForUser(userId);
        cart.setStatus(Status.LIVRE);  // Mise à jour du statut à "LIVRE"
        cartRepository.save(cart);  // Sauvegarde du panier après checkout
    }
}
