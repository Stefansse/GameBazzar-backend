package com.example.gamebazzar.service.Impl;


import com.example.gamebazzar.model.*;
import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.Cart.CartItem;
import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.OrderDTO;
import com.example.gamebazzar.repository.CartRepository;
import com.example.gamebazzar.repository.OrderItemRepository;
import com.example.gamebazzar.repository.OrderRepository;
import com.example.gamebazzar.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CartServiceImpl implements com.example.gamebazzar.service.Impl.CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartDTO createCart(User user) {
        Cart cart = new Cart(user);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.convertToDTO(savedCart);
    }

    @Override
    public Optional<CartDTO> getCartById(Long cartId) {
        return cartRepository.findById(cartId).map(cartMapper::convertToDTO);
    }

    @Override
    public Optional<CartDTO> getCartByUser(User user) {
        return cartRepository.findByUser(user).map(cartMapper::convertToDTO);
    }

    @Override
    public CartDTO addItemToCart(Long cartId, Game game, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = new CartItem(game, quantity);
        cart.addItem(cartItem);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.convertToDTO(savedCart);
    }

    @Override
    public CartDTO removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        cart.removeItem(cartItem);
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.convertToDTO(savedCart);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.clearCart();
        cartRepository.save(cart);
    }

    @Override
    public OrderDTO convertCartToOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Create a new Order
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setStatus("COMPLETED");
        order.setOrderSize(cart.getCartItems().size());
        order.setCurrency("USD");
        order.setUser(cart.getUser());

        double totalAmount = 0;

        // Calculate the total amount with discounts if applicable
        for (CartItem cartItem : cart.getCartItems()) {
            Game game = cartItem.getGame();
            double gamePrice = game.getPrice();

            // Check for an associated discount
            Discount discount = game.getDiscount();
            if (discount != null && isDiscountValid(discount)) { // Ensure the discount is valid
                double discountAmount = gamePrice * (discount.getPercentage() / 100);
                gamePrice -= discountAmount; // Apply the discount
            }

            totalAmount += gamePrice * cartItem.getQuantity();
        }

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
            Discount discount = cartItem.getGame().getDiscount();
            if (discount != null && isDiscountValid(discount)) {
                double discountAmount = itemPrice * (discount.getPercentage() / 100);
                itemPrice -= discountAmount; // Apply the discount to the order item
            }
            orderItem.setOrderItemPrice(itemPrice * cartItem.getQuantity()); // Set the item price considering the quantity

            finalSavedOrder.addOrderItem(orderItem); // Add OrderItem to the Order

            // Save the OrderItem
            orderItemRepository.save(orderItem);
        });

        // Save the order again with associated OrderItems
        orderRepository.save(finalSavedOrder);

        // Clear the cart
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return cartMapper.convertToOrderDTO(finalSavedOrder);
    }

    // Helper method to check if a discount is valid
    private boolean isDiscountValid(Discount discount) {
        LocalDate today = LocalDate.now();
        return today.isAfter(discount.getStartDate()) && today.isBefore(discount.getEndDate());
    }


}
