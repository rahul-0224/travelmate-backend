package com.example.TravelMate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TravelMate.entity.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

}