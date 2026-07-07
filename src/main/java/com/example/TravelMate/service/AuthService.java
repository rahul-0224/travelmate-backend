package com.example.TravelMate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TravelMate.dto.JwtResponse;
import com.example.TravelMate.dto.LoginRequest;
import com.example.TravelMate.dto.MessageResponse; 
import com.example.TravelMate.dto.RegisterRequest;
import com.example.TravelMate.entity.Role;
import com.example.TravelMate.entity.User;
import com.example.TravelMate.repository.UserRepository;
import com.example.TravelMate.security.JwtUtils; 

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils; 

    public MessageResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());

    
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        } else {
            user.setRole(Role.TRAVELER); 
        }

        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    public JwtResponse loginUser(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        
        
        org.springframework.security.core.userdetails.UserDetails userDetails = 
            org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities("ROLE_" + user.getRole())
            .build();

        
        String token = jwtUtils.generateToken(userDetails, user.getRole()); 
        
        return new JwtResponse(token, "Login Successful");
    }
}