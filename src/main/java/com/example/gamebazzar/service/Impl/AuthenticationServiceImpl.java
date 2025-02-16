package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.enumerations.Role;
import com.example.gamebazzar.model.jwtlogin.JwtAuthenticationResponse;
import com.example.gamebazzar.model.jwtlogin.RefreshTokenRequest;
import com.example.gamebazzar.model.jwtlogin.SignInRequest;
import com.example.gamebazzar.model.jwtlogin.SignUpRequest;
import com.example.gamebazzar.repository.CartRepository;
import com.example.gamebazzar.repository.UserRepository;
import com.example.gamebazzar.repository.WishListRepository;
import com.example.gamebazzar.service.AuthenticationService;
import com.example.gamebazzar.service.JWTService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final CartRepository cartRepository;

    private final WishListRepository wishListRepository;

    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstname());
        user.setLastName(signUpRequest.getLastname());
        user.setDateOfBirth(signUpRequest.getDateOfBirth());
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setIsGoogleAccount(false);
        user.setSub(null);

        User savedUser = userRepository.save(user);

        user.setDateJoined(LocalDate.now());


        Cart cart = new Cart();
        cart.setUser(savedUser);

        WishList wishList = new WishList();
        wishList.setCreationDate(LocalDate.now());
        wishList.setUser(savedUser);

        cartRepository.save(cart);
        wishListRepository.save(wishList);

        return savedUser;
    }


    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setUserId(user.getUserId());
        jwtAuthenticationResponse.setEmail(user.getEmail()); // Set email here
        jwtAuthenticationResponse.setDateJoined(user.getDateJoined());
        jwtAuthenticationResponse.setRole(user.getRole().name());
        jwtAuthenticationResponse.setCartId(user.getCart().getCartId());
        jwtAuthenticationResponse.setWishListId(user.getWishlist().getWishlistId());
        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }

    @Override
    public JwtAuthenticationResponse AuthenticateWithGoogle(GoogleIdToken.Payload payload) {
        User user = this.userRepository.findBySub((String) payload.get("sub"));

        if (user == null) {
            // Create a new user
            user = new User();
            user.setEmail(payload.getEmail());
            user.setFirstName((String) payload.get("given_name"));
            user.setLastName((String) payload.get("family_name"));
            user.setDateOfBirth(null);
            user.setRole(Role.ROLE_USER);
            user.setPassword(UUID.randomUUID().toString());
            user.setIsGoogleAccount(true);
            user.setSub((String) payload.get("sub"));
            user.setDateJoined(LocalDate.now());

            // Save the user first to get an ID
            User savedUser = userRepository.save(user);

            // Create and save a cart for the user
            Cart cart = new Cart();
            cart.setUser(savedUser);
            cartRepository.save(cart);

            // Create and save a wish list for the user
            WishList wishList = new WishList();
            wishList.setCreationDate(LocalDate.now());
            wishList.setUser(savedUser);
            wishListRepository.save(wishList);

            // Set the cart and wish list to the user
            savedUser.setCart(cart);
            savedUser.setWishlist(wishList);

            // Save the updated user
            userRepository.save(savedUser);

            // Generate JWT
            var jwt = jwtService.generateToken(savedUser);

            // Prepare the response
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setUserId(savedUser.getUserId());
            jwtAuthenticationResponse.setEmail(savedUser.getEmail());
            jwtAuthenticationResponse.setDateJoined(savedUser.getDateJoined());
            jwtAuthenticationResponse.setRole(savedUser.getRole().name());
            jwtAuthenticationResponse.setCartId(savedUser.getCart().getCartId());
            jwtAuthenticationResponse.setWishListId(savedUser.getWishlist().getWishlistId());

            return jwtAuthenticationResponse;
        } else {
            // User already exists
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setUserId(user.getUserId());
            jwtAuthenticationResponse.setEmail(user.getEmail());
            jwtAuthenticationResponse.setDateJoined(user.getDateJoined());
            jwtAuthenticationResponse.setRole(user.getRole().name());
            jwtAuthenticationResponse.setCartId(user.getCart().getCartId());
            jwtAuthenticationResponse.setWishListId(user.getWishlist().getWishlistId());

            return jwtAuthenticationResponse;
        }
    }



}


