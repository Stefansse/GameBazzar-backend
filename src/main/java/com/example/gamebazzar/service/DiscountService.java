package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.DiscountDTO;
import com.example.gamebazzar.model.Discount;

import java.util.List;

public interface DiscountService {

    DiscountDTO createDiscount(DiscountDTO discountDTO);

    List<DiscountDTO> getAllDiscounts();

    DiscountDTO getDiscountById(Long id);

    DiscountDTO updateDiscount(Long id, DiscountDTO discountDTO);

    void deleteDiscount(Long id);

    void applyDiscountToGame(Long discountId, Long gameId);
}