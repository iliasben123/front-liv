package com.fooddelivery.fooddeliveryapp.controllers;

import com.fooddelivery.fooddeliveryapp.entities.Cart;
import com.fooddelivery.fooddeliveryapp.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable Long userId) {
        Cart cart = cartService.getCartForUser(userId);
        double total = cart.getDishes().stream().mapToDouble(dish -> dish.getPrice() * dish.getQuantity()).sum();

        Map<String, Object> response = new HashMap<>();
        response.put("cart", cart);
        response.put("total", total);

        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{userId}/add/{dishId}")
    public ResponseEntity<?> addDishToCart(
            @PathVariable Long userId,
            @PathVariable Long dishId,
            @RequestBody Map<String, Integer> body) { // Utilisation de @RequestBody pour la quantité
        Integer quantity = body.get("quantity"); // Récupère la quantité du corps de la requête

        if (userId <= 0 || dishId <= 0 || quantity == null || quantity <= 0) {
            return ResponseEntity.badRequest().body("Invalid parameters.");
        }

        try {
            Cart cart = cartService.addDishToCart(userId, dishId, quantity);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding to cart.");
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{userId}/remove/{dishId}")
    public ResponseEntity<Cart> removeDishFromCart(@PathVariable Long userId, @PathVariable Long dishId) {
        Cart cart = cartService.removeDishFromCart(userId, dishId);
        return ResponseEntity.ok(cart);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkoutCart(@PathVariable Long userId) {
        cartService.checkoutCart(userId);
        return ResponseEntity.ok("Cart checked out successfully!");
    }
}

