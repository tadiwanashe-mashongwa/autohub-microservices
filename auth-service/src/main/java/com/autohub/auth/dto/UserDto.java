package com.autohub.auth.dto;

import com.autohub.auth.enums.Role;
import java.util.UUID;

// A "safe" representation of a User to send back in API responses
public record UserDto(
        UUID userId,
        String name,
        String email,
        Role role
) {}