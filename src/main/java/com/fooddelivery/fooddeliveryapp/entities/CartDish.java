package com.fooddelivery.fooddeliveryapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_dishes")
public class CartDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;  // Référence au panier

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;  // Référence au plat

    private int quantity; // Quantité du plat dans le panier

    // getters et setters
}

