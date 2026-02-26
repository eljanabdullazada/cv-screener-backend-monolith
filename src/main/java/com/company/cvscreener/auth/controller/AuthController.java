package com.company.cvscreener.auth.controller;

import com.company.cvscreener.auth.dto.AuthResponse;
import com.company.cvscreener.auth.dto.LoginRequest;
import com.company.cvscreener.auth.dto.RegisterRequest;
import com.company.cvscreener.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        String token = authenticationService.authenticate(
                request.username(),
                request.password()
        );

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {

        String token = authenticationService.registerCandidate(
                request.username(),
                request.password()
        );

        return new AuthResponse(token);
    }
}