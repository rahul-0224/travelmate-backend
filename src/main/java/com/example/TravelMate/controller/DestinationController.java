package com.example.TravelMate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.entity.Destination;
import com.example.TravelMate.service.DestinationService;

@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public Destination createDestination(@RequestBody Destination destination) {
        return destinationService.createDestination(destination);
    }

    @GetMapping
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public Optional<Destination> getById(@PathVariable Long id) {
        return destinationService.getDestinationById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public String deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
        return "Destination deleted successfully";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public Destination updateDestination(
            @PathVariable Long id,
            @RequestBody Destination destination) {

        return destinationService.updateDestination(id, destination);
    }
}