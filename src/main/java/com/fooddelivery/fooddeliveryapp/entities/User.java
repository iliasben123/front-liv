package com.fooddelivery.fooddeliveryapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    String name ;
    String email ;
    String password ;
    String phone ;
    String role ;
    @ManyToMany
    List<Order> orders ;



}
