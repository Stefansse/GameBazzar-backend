package com.example.gamebazzar.web;


import com.example.gamebazzar.model.DTO.CartDTO;
import com.example.gamebazzar.model.DTO.CartItemRequest;
import com.example.gamebazzar.model.DTO.OrderDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.service.GameService;
import com.example.gamebazzar.service.Impl.CartService;
import com.example.gamebazzar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "http://localhost:5173")

public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CartDTO> createCart(@RequestParam Long userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        CartDTO cartDTO = cartService.createCart(user);
        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        CartDTO cartDTO = cartService.getCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<CartDTO> addItemToCart(
            @PathVariable Long cartId,
            @RequestBody CartItemRequest cartItemRequest) {
        Game game = gameService.findGameById(cartItemRequest.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));
        CartDTO cartDTO = cartService.addItemToCart(cartId, game, cartItemRequest.getQuantity());
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        CartDTO cartDTO = cartService.removeItemFromCart(cartId, cartItemId);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{cartId}/checkout")
    public ResponseEntity<OrderDTO> checkoutCart(@PathVariable Long cartId) {
        OrderDTO orderDTO = cartService.convertCartToOrder(cartId);
        return ResponseEntity.ok(orderDTO);
    }
}
