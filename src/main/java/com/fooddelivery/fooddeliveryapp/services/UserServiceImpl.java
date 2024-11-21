package com.fooddelivery.fooddeliveryapp.services;

import com.fooddelivery.fooddeliveryapp.entities.User;
import com.fooddelivery.fooddeliveryapp.repositories.UserRepository;
import com.fooddelivery.fooddeliveryapp.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(User user) {
        // Vérifie si l'email est déjà utilisé
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Hashage du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Enregistre l'utilisateur
        return userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        // Recherche de l'utilisateur par email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        // Vérification du mot de passe
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        // Génération du jeton JWT si authentification réussie
        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        // Récupération de l'utilisateur existant
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Mise à jour des informations utilisateur
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        // Enregistrement des modifications
        return userRepository.save(existingUser);
    }

    @Override
    public User getUserProfile(Long userId) {
        // Récupération du profil utilisateur
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }

}
