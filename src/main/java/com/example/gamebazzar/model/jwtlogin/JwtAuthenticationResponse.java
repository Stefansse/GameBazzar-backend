package com.example.gamebazzar.model.jwtlogin;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private Long userId;
}