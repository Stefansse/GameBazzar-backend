package com.example.gamebazzar.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Objects;

public interface JWTService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(OAuth2User oauth2User);

    boolean isTokenValid(String token, UserDetails userDetails);

    public String generateRefreshToken(Map<String, Objects> extraClaims, UserDetails userDetails);

    Claims extractAllClaims(String token);


}