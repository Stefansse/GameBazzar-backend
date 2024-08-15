package com.example.gamebazzar.service;

import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.jwtlogin.JwtAuthenticationResponse;
import com.example.gamebazzar.model.jwtlogin.RefreshTokenRequest;
import com.example.gamebazzar.model.jwtlogin.SignInRequest;
import com.example.gamebazzar.model.jwtlogin.SignUpRequest;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}