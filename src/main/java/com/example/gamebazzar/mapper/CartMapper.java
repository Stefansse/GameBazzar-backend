package com.example.gamebazzar.mapper;


import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.Cart.CartItem;
import com.example.gamebazzar.model.DTO.*;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.OrderItem;
import com.example.gamebazzar.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getCartId());
        dto.setUser(convertToUserDTO(cart.getUser()));
        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
                .map(this::convertToCartItemDTO)
                .collect(Collectors.toList());
        dto.setCartItems(cartItemDTOs);
        return dto;
    }

    public CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setCartItemId(cartItem.getCartItemId());
        dto.setGame(convertToGameDTO(cartItem.getGame()));
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public GameDTO convertToGameDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setGameId(game.getGameId());
        dto.setTitle(game.getTitle());
        dto.setDescription(game.getDescription());
        dto.setPrice(game.getPrice());
        dto.setGenre(game.getGenre());
        dto.setPublisher(game.getPublisher());
        dto.setRating(game.getRating());
        return dto;
    }

    public UserDTO convertToUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public OrderDTO convertToOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setOrderSize(order.getOrderSize());
        dto.setCurrency(order.getCurrency());
        dto.setUser(convertToUserDTO(order.getUser()));
        List<OrderItemDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList());
        dto.setOrderItems(orderItemDTOs);
        return dto;
    }

    public OrderItemDTO convertToOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderItemId(orderItem.getOrderItemId());
        dto.setGame(convertToGameDTO(orderItem.getGame()));
        dto.setQuantity(orderItem.getQuantity());
        dto.setOrderItemPrice(orderItem.getOrderItemPrice());
        return dto;
    }
}
