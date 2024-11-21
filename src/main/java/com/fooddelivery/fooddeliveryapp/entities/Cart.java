package com.fooddelivery.fooddeliveryapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    User user;

    @ManyToMany
    List<Dish> dishes;

    @Enumerated(EnumType.ORDINAL)
    Status status;

    Double totalPrice;


}
