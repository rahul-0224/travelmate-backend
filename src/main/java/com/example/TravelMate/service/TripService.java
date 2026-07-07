package com.example.TravelMate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TravelMate.entity.Trip;
import com.example.TravelMate.entity.User;
import com.example.TravelMate.repository.TripRepository;
import com.example.TravelMate.repository.UserRepository;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;



   
    public TripService(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public List<Trip> getTripsByTraveler(String username) {
        User traveler = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return tripRepository.findByTraveler(traveler);
    }

    public Trip createTrip(Trip trip, String username) {
        User traveler = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        trip.setTraveler(traveler);
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip tripDetails) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setTitle(tripDetails.getTitle());
        trip.setStartDate(tripDetails.getStartDate());
        trip.setEndDate(tripDetails.getEndDate());
        trip.setBudget(tripDetails.getBudget());
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}