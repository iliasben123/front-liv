package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.User;

public interface UserService {
    User registerUser(User user); // Inscription d'un utilisateur
    String loginUser(String email, String password); // Connexion de l'utilisateur et génération du token JWT
    User updateUser(Long userId, User updatedUser); // Mise à jour des informations de l'utilisateur
    User getUserProfile(Long userId); // Récupération du profil de l'utilisateur
}
