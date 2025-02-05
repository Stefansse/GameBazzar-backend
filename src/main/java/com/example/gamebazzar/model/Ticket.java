package com.example.gamebazzar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "_ticket")
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String problemCategory;
    private String description;
    private String response;

    private String status; // Open, In Progress, Resolved
    private LocalDateTime submittedAt;
    private LocalDateTime resolvedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Ticket() {
        this.submittedAt = LocalDateTime.now(); // Set default submitted time when ticket is created
        this.status = "Open";
    }


    public Ticket(String name, String email, String problemCategory, String description, User user) {
        this.name = name;
        this.email = email;
        this.problemCategory = problemCategory;
        this.description = description;
        this.user = user;
    }


}
