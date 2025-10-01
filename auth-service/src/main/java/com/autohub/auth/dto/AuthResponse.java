package com.autohub.auth.dto;

// This will be the response after a successful login
public record AuthResponse(
        String accessToken
) {}