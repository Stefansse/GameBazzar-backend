package com.example.gamebazzar.web;

import com.example.gamebazzar.model.Cart.Cart;
import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.WishList;
import com.example.gamebazzar.model.enumerations.Role;
import com.example.gamebazzar.model.jwtlogin.JwtAuthenticationResponse;
import com.example.gamebazzar.repository.CartRepository;
import com.example.gamebazzar.repository.UserRepository;
import com.example.gamebazzar.repository.WishListRepository;
import com.example.gamebazzar.service.AuthenticationService;
import com.example.gamebazzar.service.JWTService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
@RequiredArgsConstructor
public class OAuth2Controller {


    private final AuthenticationService authenticationService;

    private static final String GOOGLE_CLIENT_ID = "242203406363-bgi3j580bpqstg7hctfdv0ufrh692oio.apps.googleusercontent.com";

    @PostMapping("google")
    public JwtAuthenticationResponse googleAuth(@RequestBody Map<String, String> body) throws GeneralSecurityException, IOException {
        String token = body.get("token");

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        ).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build();

        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            return authenticationService.AuthenticateWithGoogle(payload);
        }
        return null;
    }
}