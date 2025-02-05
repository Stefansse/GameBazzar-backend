package com.example.gamebazzar.model.jwtlogin;

import com.example.gamebazzar.model.enumerations.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
    private Long userId;
    private String email;
    private LocalDate dateJoined;
    private String role;
}