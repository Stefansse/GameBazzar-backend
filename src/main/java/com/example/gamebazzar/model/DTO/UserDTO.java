package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;

   public UserDTO(Long userID, String firstName, String lastName, String email) {
       this.userId = userID;
       this.firstName = firstName;
       this.lastName = lastName;
        this.email = email;
   }
}