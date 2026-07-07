package com.example.TravelMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TravelMate.entity.Itinerary;
import com.example.TravelMate.entity.Trip;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    // Custom database abstraction to fetch itineraries linked with specific trip entity rows
    List<Itinerary> findByTrip(Trip trip);
}
