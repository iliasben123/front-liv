package com.fooddelivery.fooddeliveryapp.controllers;

import com.fooddelivery.fooddeliveryapp.entities.LoginRequest;
import com.fooddelivery.fooddeliveryapp.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    // Méthode pour simuler la connexion de l'utilisateur
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        // Logique pour vérifier les informations d'identification de l'utilisateur
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            // Générer un token JWT si les informations d'identification sont valides
            return jwtUtil.generateToken(loginRequest.getUsername());
        } else {
            throw new RuntimeException("Identifiants invalides");
        }
    }
}
