package com.example.gamebazzar.model.jwtlogin;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {


    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private LocalDate dateJoined;



}