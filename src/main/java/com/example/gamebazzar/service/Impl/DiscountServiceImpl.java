package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.DTO.DiscountDTO;
import com.example.gamebazzar.model.Discount;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.repository.DiscountRepository;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.repository.UserRepository;
import com.example.gamebazzar.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public DiscountDTO createDiscount(DiscountDTO discountDTO) {
        Game game = gameRepository.findById(discountDTO.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        // Check if the game already has a discount
        if (game.getDiscount() != null) {
            throw new RuntimeException("Game already has an active discount.");
        }

        Discount discount = new Discount();
        discount.setCode(discountDTO.getCode());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());
        discount.setGame(game); // Set the game for the discount

        Discount savedDiscount = discountRepository.save(discount); // Save the discount
        game.setDiscount(savedDiscount); // Associate the discount with the game

        gameRepository.save(game);

        return convertToDTO(savedDiscount);
    }



    @Override
    public List<DiscountDTO> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        return discounts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDTO getDiscountById(Long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));
        return convertToDTO(discount);
    }

    @Override
    public DiscountDTO updateDiscount(Long id, DiscountDTO discountDTO) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount not found"));

        Game game = gameRepository.findById(discountDTO.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));


        discount.setCode((discountDTO.getCode()));
        discount.setPercentage(discountDTO.getPercentage());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());
        discount.setGame(game);


        Discount updatedDiscount = discountRepository.save(discount);
        return convertToDTO(updatedDiscount);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

    @Override
    public void applyDiscountToGame(Long discountId, Long gameId) {
        // Implementation logic to apply discount to the game
        // You can reuse the logic from your previous implementation.
    }

    // Helper method to convert Discount to DiscountDTO
    private DiscountDTO convertToDTO(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discount.getId());
        discountDTO.setCode(discount.getCode());
        discountDTO.setPercentage(discount.getPercentage());
        discountDTO.setStartDate(discount.getStartDate());
        discountDTO.setEndDate(discount.getEndDate());

        if (discount.getGame() != null) {
            discountDTO.setGameId(discount.getGame().getGameId());
        } else {
            discountDTO.setGameId(null); // or handle it accordingly
        }

        return discountDTO;
    }

}
