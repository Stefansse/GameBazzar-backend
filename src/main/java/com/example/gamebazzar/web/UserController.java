package com.example.gamebazzar.web;

import com.example.gamebazzar.model.User;
import com.example.gamebazzar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    // Find User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Find User by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Users
    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    // Update a User
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
////        // You might want to ensure that the user ID in the path and body match
////        if (!id.equals(user.getUserId())) {
////            return ResponseEntity.badRequest().build();
////        }
//        User updatedUser = userService.addUser(user);
//        return ResponseEntity.ok(updatedUser);
//    }

    // Delete a User by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
