package com.example.gamebazzar.service;

import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.PaymentResponse;
import com.example.gamebazzar.model.Order;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface PaymentService {

    PaymentResponse createPaymentService(Order order);

    Session createCheckoutSession(CartDTO cartDTO) throws StripeException;
}
