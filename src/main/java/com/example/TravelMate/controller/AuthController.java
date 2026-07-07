package com.example.TravelMate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.dto.JwtResponse;
import com.example.TravelMate.dto.LoginRequest;
import com.example.TravelMate.dto.RegisterRequest;
import com.example.TravelMate.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
        @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                authService.registerUser(request));
    }
    @PostMapping("/login")
public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginRequest request) {

    return ResponseEntity.ok(authService.loginUser(request));
}
}
