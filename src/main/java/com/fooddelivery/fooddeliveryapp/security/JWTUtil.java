package com.fooddelivery.fooddeliveryapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private String secretKey = "yourSecretKey"; // Clé secrète pour signer le JWT

    // Méthode pour générer un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiration après 10 heures
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Extraire les claims du token
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraire le nom d'utilisateur (subject) du token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Vérifier si le token est expiré
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valider le token
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
