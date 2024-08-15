package com.example.gamebazzar.service.Impl;


import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.Cart.CartItem;
import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.OrderDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.OrderItem;
import com.example.gamebazzar.model.User;
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
        order.setTotalAmount(cart.getCartItems().stream()
                .mapToDouble(item -> item.getGame().getPrice() * item.getQuantity())
                .sum());
        order.setStatus("COMPLETED");
        order.setOrderSize(cart.getCartItems().size());
        order.setCurrency("USD");
        order.setUser(cart.getUser());

        // Save the order first
        Order savedOrder = orderRepository.save(order);

        // Create OrderItems and associate them with the saved Order
        Order finalSavedOrder = savedOrder;
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setGame(cartItem.getGame());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(finalSavedOrder); // Associate with the saved Order
            orderItem.setOrderItemPrice(cartItem.getGame().getPrice() * cartItem.getQuantity());
            finalSavedOrder.addOrderItem(orderItem); // Add OrderItem to the Order

            // Save the OrderItem
            orderItemRepository.save(orderItem);
        });

        // Save the order again with associated OrderItems
        savedOrder = orderRepository.save(savedOrder);

        // Clear the cart
        cart.getCartItems().clear();
        cartRepository.save(cart);

        return cartMapper.convertToOrderDTO(savedOrder);
    }

}
