package com.example.gamebazzar.model.DTO;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String body;


    public EmailRequest() {}

    public EmailRequest(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }


}
