package com.example.gamebazzar.web;

import com.example.gamebazzar.model.DTO.AddGameToWishListDTO;
import com.example.gamebazzar.model.DTO.CreateWishListDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.WishListItem;
import com.example.gamebazzar.service.WishListService;
import com.example.gamebazzar.service.UserService;
import com.example.gamebazzar.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
@CrossOrigin(origins = "http://localhost:5173")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;




    @PostMapping("/create")
    public ResponseEntity<WishList> createWishList(@RequestBody CreateWishListDTO createWishListDTO) {
        // Create the new wishlist using the DTO
        WishList newWishList = wishListService.createWishList(createWishListDTO);

        // Return the created wishlist in the response
        return ResponseEntity.ok(newWishList);
    }

    // Get all wishlists for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishList>> getWishListsByUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<WishList> wishLists = wishListService.getWishListsByUser(user);
        return ResponseEntity.ok(wishLists);
    }

    // Add a game to a wishlist
    @PostMapping("/{wishlistId}/add")
    public ResponseEntity<WishList> addGameToWishList(@PathVariable Long wishlistId, @RequestBody AddGameToWishListDTO request) {
        WishList updatedWishList = wishListService.addGameToWishList(wishlistId, request);
        return ResponseEntity.ok(updatedWishList);
    }


    @PutMapping("/update/{wishlistId}")
    public ResponseEntity<WishList> updateWishList(
            @PathVariable Long wishlistId,
            @RequestBody CreateWishListDTO request) {
        WishList updatedWishList = wishListService.updateWishList(wishlistId, request);
        return ResponseEntity.ok(updatedWishList);
    }

    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<Void> deleteWishList(@PathVariable Long wishlistId) {
        wishListService.deleteWishList(wishlistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clear/{wishlistId}")
    public ResponseEntity<Void> clearWishList(@PathVariable Long wishlistId) {
        wishListService.clearWishList(wishlistId);
        return ResponseEntity.noContent().build();
    }
}
