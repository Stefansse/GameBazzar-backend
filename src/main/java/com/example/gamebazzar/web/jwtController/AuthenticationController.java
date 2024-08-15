package com.example.gamebazzar.web.jwtController;

import com.example.gamebazzar.model.User;
import com.example.gamebazzar.model.jwtlogin.JwtAuthenticationResponse;
import com.example.gamebazzar.model.jwtlogin.RefreshTokenRequest;
import com.example.gamebazzar.model.jwtlogin.SignInRequest;
import com.example.gamebazzar.model.jwtlogin.SignUpRequest;
import com.example.gamebazzar.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "https://blog-buster-fronted.vercel.app/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;




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


}