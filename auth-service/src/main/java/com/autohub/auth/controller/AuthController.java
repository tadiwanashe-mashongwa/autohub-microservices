package com.autohub.auth.controller;

import com.autohub.auth.dto.AuthResponse;
import com.autohub.auth.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import com.autohub.auth.dto.RegisterRequest;
import com.autohub.auth.dto.UserDto;
import com.autohub.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth") // Base URL for all endpoints in this controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register") // Handles POST requests to /api/v1/auth/register
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request) {
        UserDto newUser = authService.registerUser(request);
        // Return a 201 Created status, which is the standard for successful creation
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}