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
            // Initialize the session parameters builder
            SessionCreateParams.Builder sessionParamsBuilder = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)  // Specify payment method type
                    .setMode(SessionCreateParams.Mode.PAYMENT)  // Specify payment mode
                    .setSuccessUrl("https://your-domain.com/success")  // Success URL
                    .setCancelUrl("https://your-domain.com/cancel");  // Cancel URL

            // Initialize line items list
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

            // Loop through order items and create line items
            for (OrderItem orderItem : order.getOrderItems()) {
                long amountInCents = (long) (orderItem.getGame().getPrice() * 100); // Convert price to cents

                // Build a line item for each order item
                SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                        .setQuantity(Long.valueOf(orderItem.getQuantity())) // Quantity of the item
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("usd")  // Currency
                                        .setUnitAmount(amountInCents)  // Unit amount in cents
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(orderItem.getGame().getTitle())  // Game name
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

                // Add the line item to the list
                lineItems.add(lineItem);
            }

            // Add all line items to the session parameters
            sessionParamsBuilder.addAllLineItem(lineItems);

            // Create the Stripe session
            Session session = Session.create(SessionCreateParams.builder().build());

            PaymentResponse response = new PaymentResponse();

            // Return the session ID as the response
            response.setPayment_url(session.getUrl());

            return response;

        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe session", e);
        }
    }

    public Session createCheckoutSession(CartDTO cartDTO) {
        Stripe.apiKey = stripeSecurityKey;
        try {
            // Map cart items to Stripe line items, applying the discount if available
            List<SessionCreateParams.LineItem> lineItems = cartDTO.getCartItems().stream()
                    .map(item -> {
                        double price = item.getGame().getPrice();
                        double discount = item.getGame().getDiscount() != null ? item.getGame().getDiscount().getPercentage() : 0;

                        // Apply discount if applicable
                        if (discount > 0) {
                            price = price * (1 - discount / 100);
                        }

                        return SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount((long) (price * 100))  // Convert price to cents after discount
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(item.getGame().getTitle())  // Assuming getGame returns the correct game object
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity((long) item.getQuantity())  // Ensure you're getting the quantity from the DTO
                                .build();
                    })
                    .collect(Collectors.toList());

            // Get the cart from the repository
            Cart cart = cartRepository.findById(cartDTO.getCartId())
                    .orElseThrow(() -> new RuntimeException("Cart not found"));

            // Create the order
            Order order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setStatus("COMPLETED");
            order.setOrderSize(cart.getCartItems().size());
            order.setCurrency("USD");
            order.setUser(cart.getUser());

            double totalAmount = 0;

            // Calculate the total amount with discounts applied
            for (CartItem cartItem : cart.getCartItems()) {
                Game game = cartItem.getGame();
                double gamePrice = game.getPrice();

                // Apply discount if available
                Discount discount = game.getDiscount();
                if (discount != null) { // Ensure the discount is valid
                    double discountAmount = gamePrice * (discount.getPercentage() / 100);
                    gamePrice -= discountAmount; // Apply the discount
                }

                // Add the discounted price to totalAmount, considering the quantity
                totalAmount += gamePrice * cartItem.getQuantity();
            }

            // Set the total amount with discount applied
            order.setTotalAmount(totalAmount);

            // Save the order first
            Order savedOrder = orderRepository.save(order);

            // Create OrderItems and associate them with the saved Order
            final Order finalSavedOrder = savedOrder;  // Create a final variable to use in lambda
            cart.getCartItems().forEach(cartItem -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setGame(cartItem.getGame());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setOrder(finalSavedOrder); // Associate with the saved Order

                // Calculate the order item price considering the discount
                double itemPrice = cartItem.getGame().getPrice();
                Discount itemDiscount = cartItem.getGame().getDiscount();
                if (itemDiscount != null) {
                    double discountAmount = itemPrice * (itemDiscount.getPercentage() / 100);
                    itemPrice -= discountAmount; // Apply the discount to the order item
                }
                orderItem.setOrderItemPrice(itemPrice * cartItem.getQuantity()); // Set the item price considering the quantity

                finalSavedOrder.addOrderItem(orderItem); // Add OrderItem to the Order

                // Save the OrderItem
                orderItemRepository.save(orderItem);
            });

            // Save the order again with associated OrderItems
            orderRepository.save(finalSavedOrder);

            // Clear the cart items
            cart.getCartItems().clear();
            cartRepository.save(cart);

            // Prepare the email body with the discounted total
            StringBuilder gameNames = new StringBuilder();
            StringBuilder gameCodes = new StringBuilder();

            for (OrderItem orderItem : savedOrder.getOrderItems()) {
                Game game = orderItem.getGame();
                String gameCode = generateRandomGameCode(); // Generate a random code for each game

                gameNames.append(game.getTitle()).append(", ");
                gameCodes.append("\n- ").append(game.getTitle()).append(" (Game Code: ").append(gameCode).append(")\n");
            }

            // Remove the last comma and space if there are any game names
            if (gameNames.length() > 0) {
                gameNames.setLength(gameNames.length() - 2);
            }

            DecimalFormat df = new DecimalFormat("#.00");
            String formattedTotalAmount = df.format(totalAmount);
            // Prepare email details
            String userEmail = cart.getUser().getEmail(); // Assuming the User entity has an email field
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

            // Create session
            return Session.create(params);  // This may throw a StripeException if something goes wrong
        } catch (StripeException e) {
            // Log the Stripe error message
            System.out.println("StripeException: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error creating Stripe checkout session", e); // You can return a custom error or rethrow
        } catch (Exception e) {
            // Catch general exceptions
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();

            throw new RuntimeException("Unexpected error occurred", e);
        }
    }


}
