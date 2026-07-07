package com.example.TravelMate.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "itineraries")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dayNumber;
    private String activity;
    private BigDecimal estimatedExpense;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties({"itineraries", "expenses", "traveler"})
    private Trip trip;
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    
    public Itinerary() {
    }

   
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getDayNumber() { return dayNumber; }
    public void setDayNumber(Integer dayNumber) { this.dayNumber = dayNumber; }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }

    public BigDecimal getEstimatedExpense() { return estimatedExpense; }
    public void setEstimatedExpense(BigDecimal estimatedExpense) { this.estimatedExpense = estimatedExpense; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
}