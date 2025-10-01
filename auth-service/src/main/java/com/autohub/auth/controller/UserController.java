package com.autohub.auth.controller;

import com.autohub.auth.dto.UserDto;
import com.autohub.auth.mapper.UserMapper;
import com.autohub.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        // Spring Security provides the authenticated user's details via the Authentication object.
        // The 'principal' is the username we set in the UserDetailsService (which is the email).
        String userEmail = authentication.getName();

        // Find the user in the database and convert it to a safe DTO before returning.
        return userRepository.findByEmail(userEmail)
                .map(userMapper::userToUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}