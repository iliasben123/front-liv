package com.fooddelivery.fooddeliveryapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Commentaires")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    @ManyToOne
    User user ;
    @ManyToOne
    Dish dish ;
    Integer rating ;
    String comment  ;

}

