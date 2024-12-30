package com.fooddelivery.fooddeliveryapp.controllers;

import com.fooddelivery.fooddeliveryapp.entities.User;
import com.fooddelivery.fooddeliveryapp.security.JWTUtil;
import com.fooddelivery.fooddeliveryapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestParam Long userId, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extraire le token JWT de l'en-tête Authorization
            String token = authorizationHeader.replace("Bearer ", "");

            // Valider le token
            if (!jwtUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide ou expiré");
            }

            // Vérifier que l'ID utilisateur dans le token correspond à l'ID demandé
            String loggedInUserId = jwtUtil.extractUserId(token);
            if (!loggedInUserId.equals(userId.toString())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé");
            }

            // Récupérer le profil de l'utilisateur
            User user = userService.getUserProfile(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur d'authentification");
        }
    }
}
