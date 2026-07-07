package com.example.TravelMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TravelMate.entity.Trip;
import com.example.TravelMate.entity.User;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByTraveler(User traveler);
}