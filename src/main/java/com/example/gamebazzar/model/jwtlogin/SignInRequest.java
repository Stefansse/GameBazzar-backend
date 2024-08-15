package com.example.gamebazzar.model.jwtlogin;

import lombok.Data;

@Data
public class SignInRequest {

    public String email;

    public String password;
}