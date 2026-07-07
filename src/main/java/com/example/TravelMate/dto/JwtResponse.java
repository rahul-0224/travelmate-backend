package com.example.TravelMate.dto;

public class JwtResponse {
    private String accessToken;
    private String message;

    public JwtResponse(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }

    public String getAccessToken() { return accessToken; }
    public String getMessage() { return message; }
}