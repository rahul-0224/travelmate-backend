package com.example.TravelMate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TravelMate.entity.Itinerary;
import com.example.TravelMate.entity.Trip;
import com.example.TravelMate.repository.ItineraryRepository;
import com.example.TravelMate.repository.TripRepository;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final TripRepository tripRepository;

    
    public ItineraryService(ItineraryRepository itineraryRepository, TripRepository tripRepository) {
        this.itineraryRepository = itineraryRepository;
        this.tripRepository = tripRepository;
    }

    public List<Itinerary> getItinerariesByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        return itineraryRepository.findByTrip(trip);
    }

    public Itinerary addItinerary(Long tripId, Itinerary itinerary) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        itinerary.setTrip(trip);
        return itineraryRepository.save(itinerary);
    }

    public void deleteItinerary(Long id) {
        itineraryRepository.deleteById(id);
    }
}