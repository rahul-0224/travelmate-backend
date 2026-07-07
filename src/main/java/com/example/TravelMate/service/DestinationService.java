package com.example.TravelMate.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TravelMate.entity.Destination;
import com.example.TravelMate.repository.DestinationRepository;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;


    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    
    public Optional<Destination> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }


    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

    
    public Destination updateDestination(Long id, Destination updatedDestination) {

        Destination existing = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        existing.setPlaceName(updatedDestination.getPlaceName());
        existing.setLocation(updatedDestination.getLocation());
        existing.setDescription(updatedDestination.getDescription());
        existing.setPrice(updatedDestination.getPrice());
        existing.setImageUrl(updatedDestination.getImageUrl());

        return destinationRepository.save(existing);
    }
}