package com.example.gamebazzar.web.jwtController;

import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.jwtlogin.JwtAuthenticationResponse;
import com.example.gamebazzar.model.jwtlogin.RefreshTokenRequest;
import com.example.gamebazzar.model.jwtlogin.SignInRequest;
import com.example.gamebazzar.model.jwtlogin.SignUpRequest;
import com.example.gamebazzar.service.AuthenticationService;
import com.example.gamebazzar.service.Impl.JWTServiceImpl;
import com.example.gamebazzar.service.JWTService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JWTServiceImpl jwtService;


    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        //SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstname(signUpRequest.getFirstname());
        signUpRequest.setLastname(signUpRequest.getLastname());
        signUpRequest.setEmail(signUpRequest.getEmail());
        signUpRequest.setPassword(signUpRequest.getPassword());
        //signUpRequest.setImage(image);

        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        // Extract the token from the Authorization header
        String token = authHeader.replace("Bearer ", "");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body("Token is missing");
        }

        try {
            // Extract claims from the token
            Claims claims = jwtService.extractAllClaims(token);

            // Check if the token is expired
            if (jwtService.isTokenExpired(token)) {
                return ResponseEntity.status(401).body("Token is expired");
            }

            // If the token is valid, return success
            return ResponseEntity.ok("Token is valid");

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Token validation failed: " + e.getMessage());
        }
    }



}