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

    // Create a new ticket and send an email confirmation
    public Ticket createTicket(TicketDTO ticketDto, User user) {
        Ticket ticket = new Ticket();
        ticket.setName(ticketDto.getName());
        ticket.setEmail(sanitizeQuotes(ticketDto.getEmail()));
        ticket.setProblemCategory(ticketDto.getProblemCategory());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setStatus("Open");
        ticket.setSubmittedAt(LocalDateTime.now());
        ticket.setUser(user);

        // Save the ticket to the database
        Ticket savedTicket = ticketRepository.save(ticket);

        // Send email confirmation to the user
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

    // Update ticket status and notify the user
    public Optional<Ticket> updateTicketStatus(Long ticketId, String status) {
        // Find the ticket by its ID
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        // If ticket exists, proceed with updating its status
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();

            // Check if the new status is different from the current status
            if (!ticket.getStatus().equals(status)) {
                ticket.setStatus(status);

                // If the status is 'Resolved', set the resolved timestamp
                if (status.equals("Resolved")) {
                    ticket.setResolvedAt(LocalDateTime.now());
                }

                // Save the updated ticket back to the database
                Ticket updatedTicket = ticketRepository.save(ticket);

                // Send an email notification to the user about the ticket status update
                String emailSubject = "Ticket Update: #" + updatedTicket.getId();
                String emailBody = "Dear " + updatedTicket.getName() + ",\n\n"
                        + "The status of your ticket has been updated:\n"
                        + "Ticket ID: " + updatedTicket.getId() + "\n"
                        + "Status: " + updatedTicket.getStatus() + "\n\n"
                        + "Best regards,\nSupport Team";

                // Call your email service to send the email
                emailService.sendEmail(updatedTicket.getEmail(), emailSubject, emailBody);

                // Return the updated ticket
                return Optional.of(updatedTicket);
            } else {
                // If the status hasn't changed, no update is necessary
                return Optional.empty();
            }
        }

        // Return an empty optional if the ticket doesn't exist
        return Optional.empty();
    }




    @Override
    public List<Ticket> getTicketsByUserId(Long userId) {
        // Check if the current user is an admin
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            // If admin, return all tickets
            return ticketRepository.findAll();
        } else {
            // If not admin, return tickets for the specific user
            return ticketRepository.findByUserId(userId);
        }// This method should be defined in TicketRepository
    }

    @Override
    @Transactional
    public Optional<Ticket> updateTicketResponse(Long ticketId, TicketDTO ticketDTO) {
        // Extract the response from the DTO
        String response = ticketDTO.getResponse();

        // Validate the response (it should be plain text)
        if (response == null || response.trim().isEmpty()) {
            return Optional.empty(); // Return empty if the response is invalid
        }

        // Fetch the ticket by ID
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);

        // If the ticket doesn't exist, return Optional.empty()
        if (ticketOptional.isEmpty()) {
            return Optional.empty();
        }

        // Ticket exists, so update it
        Ticket ticket = ticketOptional.get();
        ticket.setResponse(response); // Set the admin's response as plain text

        // Save the updated ticket back to the database
        ticketRepository.save(ticket);

        // Return the updated ticket
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
