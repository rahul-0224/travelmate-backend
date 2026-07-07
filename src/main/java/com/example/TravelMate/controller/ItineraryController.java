package com.example.TravelMate.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.entity.Itinerary;
import com.example.TravelMate.service.ItineraryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/trips/{tripId}/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public List<Itinerary> getItineraries(@PathVariable Long tripId) {
        return itineraryService.getItinerariesByTrip(tripId);
    }

    @PostMapping
    @PreAuthorize("hasRole('TRAVELER')") 
    public Itinerary addItinerary(@PathVariable Long tripId, @RequestBody Itinerary itinerary) {
        return itineraryService.addItinerary(tripId, itinerary);
    }
}