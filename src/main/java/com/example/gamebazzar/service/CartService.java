package com.example.gamebazzar.service.Impl;


import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.Cart.CartItem;
import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.OrderDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.Order;
import com.example.gamebazzar.model.User;

import java.util.Optional;

public interface CartService {

    CartDTO createCart(User user);

    Optional<CartDTO> getCartById(Long cartId);

    Optional<CartDTO> getCartByUser(User user);

    CartDTO addItemToCart(Long cartId, Game game, int quantity);

    CartDTO removeItemFromCart(Long cartId, Long cartItemId);

    void clearCart(Long cartId);

    OrderDTO convertCartToOrder(Long cartId);
}
