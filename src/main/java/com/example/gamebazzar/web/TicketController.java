package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.TicketDTO;
import com.example.gamebazzar.model.Ticket;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.service.TicketService;
import com.example.gamebazzar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    private final UserService userService;

    public TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    // Endpoint to create a new ticket
    @PostMapping
    public Ticket createTicket(@RequestBody TicketDTO ticketDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        // Remove quotation marks from the email
        String email = authentication.getName().replace("\"", "");
        System.out.println("Authenticated user's email (cleaned): " + email);

        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found with email: " + email));

        return ticketService.createTicket(ticketDto, user);
    }



    // Endpoint to update ticket status
    @PutMapping("/{ticketId}/status")
    public Optional<Optional<Ticket>> updateTicketStatus(@PathVariable Long ticketId, @RequestParam String status) {
        return Optional.ofNullable(ticketService.updateTicketStatus(ticketId, status));
    }

    //Endpoint to get user ticket
    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.getTicketsByUserId(userId); // This method will fetch tickets based on user ID
    }

    // Endpoint to get a specific ticket by ID
    @GetMapping("/{ticketId}")
    public Optional<Optional<Ticket>> getTicketById(@PathVariable Long ticketId) {
        return Optional.ofNullable(ticketService.getTicketById(ticketId));
    }

    @PutMapping("/{ticketId}/response")
    public ResponseEntity<Ticket> updateTicketResponse(
            @PathVariable Long ticketId,
            @RequestBody TicketDTO ticketDTO) {  // Use TicketDTO to accept all ticket details, including the response

        // Call the service method to update the ticket's response
        Optional<Ticket> updatedTicket = ticketService.updateTicketResponse(ticketId, ticketDTO);

        // If the ticket was updated successfully, return it with HTTP 200 OK
        if (updatedTicket.isPresent()) {
            return ResponseEntity.ok(updatedTicket.get());
        }

        // If the response is invalid or the ticket does not exist, return HTTP 400 Bad Request
        return ResponseEntity.badRequest().build();
    }

}
