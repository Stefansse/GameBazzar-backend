package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.TicketDTO;
import com.example.gamebazzar.model.Ticket;
import com.example.gamebazzar.model.User;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket createTicket(TicketDTO ticketDTO, User user);

    Optional<Ticket> getTicketById(Long ticketId);
    List<Ticket> getAllTickets();

    Optional<Ticket> updateTicketStatus(Long ticketId, String status);

    List<Ticket> getTicketsByUserId(Long userId);

    Optional<Ticket> updateTicketResponse(Long ticketId, TicketDTO ticketDTO);




}
