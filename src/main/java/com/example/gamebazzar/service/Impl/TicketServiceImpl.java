package com.example.gamebazzar.service.Impl;


import com.example.gamebazzar.model.DTO.TicketDTO;
import com.example.gamebazzar.model.Ticket;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.repository.TicketRepository;
import com.example.gamebazzar.service.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.gamebazzar.model.utils.StringSanitizerUtil.sanitizeQuotes;

@Service
public class TicketServiceImpl implements TicketService {


    private final TicketRepository ticketRepository;

    private static final List<SseEmitter> emitters = new ArrayList<>();



    private final EmailService emailService;

    public TicketServiceImpl(TicketRepository ticketRepository, EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.emailService = emailService;
    }


    public Ticket createTicket(TicketDTO ticketDto, User user) {
        Ticket ticket = new Ticket();
        ticket.setName(ticketDto.getName());
        ticket.setEmail(sanitizeQuotes(ticketDto.getEmail()));
        ticket.setProblemCategory(ticketDto.getProblemCategory());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setStatus("Open");
        ticket.setSubmittedAt(LocalDateTime.now());
        ticket.setUser(user);


        Ticket savedTicket = ticketRepository.save(ticket);


        String emailSubject = "Ticket Submitted: #" + savedTicket.getId();
        String emailBody = "Dear " + savedTicket.getName() + ",\n\n"
                + "Your ticket has been successfully submitted with the following details:\n"
                + "Ticket ID: " + savedTicket.getId() + "\n"
                + "Category: " + savedTicket.getProblemCategory() + "\n"
                + "Description: " + savedTicket.getDescription() + "\n"
                + "Status: " + savedTicket.getStatus() + "\n\n"
                + "We will get back to you as soon as possible.\n\n"
                + "Best regards,\nSupport Team";

        emailService.sendEmail(savedTicket.getEmail(), emailSubject, emailBody);

        return savedTicket;
    }


    public Optional<Ticket> updateTicketStatus(Long ticketId, String status) {

        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);


        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();


            if (!ticket.getStatus().equals(status)) {
                ticket.setStatus(status);


                if (status.equals("Resolved")) {
                    ticket.setResolvedAt(LocalDateTime.now());
                }


                Ticket updatedTicket = ticketRepository.save(ticket);


                String emailSubject = "Ticket Update: #" + updatedTicket.getId();
                String emailBody = "Dear " + updatedTicket.getName() + ",\n\n"
                        + "The status of your ticket has been updated:\n"
                        + "Ticket ID: " + updatedTicket.getId() + "\n"
                        + "Status: " + updatedTicket.getStatus() + "\n\n"
                        + "Best regards,\nSupport Team";


                emailService.sendEmail(updatedTicket.getEmail(), emailSubject, emailBody);


                return Optional.of(updatedTicket);
            } else {

                return Optional.empty();
            }
        }


        return Optional.empty();
    }




    @Override
    public List<Ticket> getTicketsByUserId(Long userId) {

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {

            return ticketRepository.findAll();
        } else {

            return ticketRepository.findByUserId(userId);
        }//
    }

    @Override
    @Transactional
    public Optional<Ticket> updateTicketResponse(Long ticketId, TicketDTO ticketDTO) {

        String response = ticketDTO.getResponse();


        if (response == null || response.trim().isEmpty()) {
            return Optional.empty();
        }


        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);


        if (ticketOptional.isEmpty()) {
            return Optional.empty();
        }


        Ticket ticket = ticketOptional.get();
        ticket.setResponse(response);


        ticketRepository.save(ticket);


        return Optional.of(ticket);
    }


    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // Find a ticket by ID
    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
