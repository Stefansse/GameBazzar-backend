package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.DiscountDTO;
import com.example.gamebazzar.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    // Create a new discount
    @PostMapping("/create")
    public ResponseEntity<DiscountDTO> createDiscount(@RequestBody DiscountDTO discountDTO) {
        DiscountDTO createdDiscount = discountService.createDiscount(discountDTO);
        return ResponseEntity.ok(createdDiscount);
    }

    // Get all discounts
    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = discountService.getAllDiscounts();
        return ResponseEntity.ok(discounts);
    }

    // Get a discount by ID
    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Long id) {
        DiscountDTO discount = discountService.getDiscountById(id);
        return ResponseEntity.ok(discount);
    }

    // Update a discount
    @PutMapping("/{id}")
    public ResponseEntity<DiscountDTO> updateDiscount(@PathVariable Long id, @RequestBody DiscountDTO discountDTO) {
        DiscountDTO updatedDiscount = discountService.updateDiscount(id, discountDTO);
        return ResponseEntity.ok(updatedDiscount);
    }

    // Delete a discount
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }

    // Apply discount to a game
    @PostMapping("/{discountId}/apply-to-game/{gameId}")
    public ResponseEntity<Void> applyDiscountToGame(@PathVariable Long discountId, @PathVariable Long gameId) {
        discountService.applyDiscountToGame(discountId, gameId);
        return ResponseEntity.noContent().build();
    }
}