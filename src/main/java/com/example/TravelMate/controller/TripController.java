package com.example.TravelMate.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.entity.Trip;
import com.example.TravelMate.exception.ResourceNotFoundException;
import com.example.TravelMate.repository.TripRepository; 
import com.example.TravelMate.service.TripService; 

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;
    private final TripRepository tripRepository; 

    public TripController(TripService tripService, TripRepository tripRepository) {
        this.tripService = tripService;
        this.tripRepository = tripRepository;
    }

@GetMapping
@PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')")
public List<Trip> getAllTrips(@RequestParam(value = "search", required = false) String search,
                               Authentication authentication) {

    boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

    if (isAdmin) {
        return tripService.getAllTrips();
    }

    String username = authentication.getName();
    return tripService.getTripsByTraveler(username);
}

@PostMapping
@PreAuthorize("hasRole('TRAVELER')")
public Trip createTrip(@RequestBody Trip trip, Authentication authentication) {
    trip.setItineraries(null);
    trip.setExpenses(null);
    String username = authentication.getName();
    return tripService.createTrip(trip, username);
}

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TRAVELER')") 
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip tripDetails) {
        
        Trip existingTrip = tripRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Trip record missing in database for update " + id));
            
        existingTrip.setTitle(tripDetails.getTitle());
        existingTrip.setCategory(tripDetails.getCategory());
        existingTrip.setStartDate(tripDetails.getStartDate());
        existingTrip.setEndDate(tripDetails.getEndDate());
        existingTrip.setBudget(tripDetails.getBudget());
        
        return tripService.updateTrip(id, existingTrip);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        
        tripRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Trip record missing in database for delete, mapla! ID: " + id));
            
        tripService.deleteTrip(id);
        return ResponseEntity.ok().build();
    }
}



