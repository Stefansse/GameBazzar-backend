package com.example.gamebazzar.model.DTO;


import lombok.Data;

@Data
public class TicketDTO {
    private String name;
    private String email;
    private Long userId;
    private String problemCategory;
    private String description;
    private String response;

}
