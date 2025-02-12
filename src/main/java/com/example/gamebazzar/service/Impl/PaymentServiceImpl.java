package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.Cart.CartItem;
import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.PaymentResponse;
import com.example.gamebazzar.model.Discount;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.OrderItem;
import com.example.gamebazzar.repository.CartRepository;
import com.example.gamebazzar.repository.OrderItemRepository;
import com.example.gamebazzar.repository.OrderRepository;
import com.example.gamebazzar.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.gamebazzar.model.utils.StringSanitizerUtil.generateRandomGameCode;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecurityKey;

    private final CartRepository cartRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final EmailService emailService;

    public PaymentServiceImpl(CartRepository cartRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmailService emailService) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.emailService = emailService;
    }

    @Override
    public PaymentResponse createPaymentService(Order order) {
        // Set the Stripe API key
        Stripe.apiKey = stripeSecurityKey;

        try {

            SessionCreateParams.Builder sessionParamsBuilder = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("https://your-domain.com/success")
                    .setCancelUrl("https://your-domain.com/cancel");


            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();


            for (OrderItem orderItem : order.getOrderItems()) {
                long amountInCents = (long) (orderItem.getGame().getPrice() * 100);


                SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                        .setQuantity(Long.valueOf(orderItem.getQuantity()))
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")
                                        .setUnitAmount(amountInCents)
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(orderItem.getGame().getTitle())
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();


                lineItems.add(lineItem);
            }


            sessionParamsBuilder.addAllLineItem(lineItems);

            Session session = Session.create(SessionCreateParams.builder().build());
            PaymentResponse response = new PaymentResponse();
            response.setPayment_url(session.getUrl());

            return response;

        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe session", e);
        }
    }

    public Session createCheckoutSession(CartDTO cartDTO) {
        Stripe.apiKey = stripeSecurityKey;
        try {

            List<SessionCreateParams.LineItem> lineItems = cartDTO.getCartItems().stream()
                    .map(item -> {
                        double price = item.getGame().getPrice();
                        double discount = item.getGame().getDiscount() != null ? item.getGame().getDiscount().getPercentage() : 0;


                        if (discount > 0) {
                            price = price * (1 - discount / 100);
                        }

                        return SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount((long) (price * 100))
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(item.getGame().getTitle())
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity((long) item.getQuantity())
                                .build();
                    })
                    .collect(Collectors.toList());


            Cart cart = cartRepository.findById(cartDTO.getCartId())
                    .orElseThrow(() -> new RuntimeException("Cart not found"));


            Order order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setStatus("COMPLETED");
            order.setOrderSize(cart.getCartItems().size());
            order.setCurrency("USD");
            order.setUser(cart.getUser());

            double totalAmount = 0;


            for (CartItem cartItem : cart.getCartItems()) {
                Game game = cartItem.getGame();
                double gamePrice = game.getPrice();


                Discount discount = game.getDiscount();
                if (discount != null) {
                    double discountAmount = gamePrice * (discount.getPercentage() / 100);
                    gamePrice -= discountAmount;
                }


                totalAmount += gamePrice * cartItem.getQuantity();
            }


            order.setTotalAmount(totalAmount);


            Order savedOrder = orderRepository.save(order);


            final Order finalSavedOrder = savedOrder;
            cart.getCartItems().forEach(cartItem -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setGame(cartItem.getGame());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setOrder(finalSavedOrder);


                double itemPrice = cartItem.getGame().getPrice();
                Discount itemDiscount = cartItem.getGame().getDiscount();
                if (itemDiscount != null) {
                    double discountAmount = itemPrice * (itemDiscount.getPercentage() / 100);
                    itemPrice -= discountAmount;
                }
                orderItem.setOrderItemPrice(itemPrice * cartItem.getQuantity());

                finalSavedOrder.addOrderItem(orderItem);

                // Save the OrderItem
                orderItemRepository.save(orderItem);
            });


            orderRepository.save(finalSavedOrder);


            cart.getCartItems().clear();
            cartRepository.save(cart);


            StringBuilder gameNames = new StringBuilder();
            StringBuilder gameCodes = new StringBuilder();

            for (OrderItem orderItem : savedOrder.getOrderItems()) {
                Game game = orderItem.getGame();
                String gameCode = generateRandomGameCode();

                gameNames.append(game.getTitle()).append(", ");
                gameCodes.append("\n- ").append(game.getTitle()).append(" (Game Code: ").append(gameCode).append(")\n");
            }


            if (gameNames.length() > 0) {
                gameNames.setLength(gameNames.length() - 2);
            }

            DecimalFormat df = new DecimalFormat("#.00");
            String formattedTotalAmount = df.format(totalAmount);
            // Prepare email details
            String userEmail = cart.getUser().getEmail();
            String subject = "Thank You for Your Purchase!";
            String body = "Dear " + cart.getUser().getFirstName() + ",\n\n" +
                    "Thank you for your purchase! Your order for the game(s) has/have been successfully placed.\n" +
                    "Here are your game codes:\n" + gameCodes.toString() +
                    "We hope you enjoy your games. \n\n" +
                    "Total Amount: $" + formattedTotalAmount + "\n\n" +
                    "Best regards,\nGameBazaar Team";

            emailService.sendEmail(userEmail, subject, body);

            // Create Stripe Checkout Session
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:5173/success")
                    .setCancelUrl("http://localhost:5173/cancel")
                    .addAllLineItem(lineItems)
                    .build();


            return Session.create(params);
        } catch (StripeException e) {

            System.out.println("StripeException: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error creating Stripe checkout session", e);
        } catch (Exception e) {

            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Unexpected error occurred", e);
        }
    }


}
