package com.example.TravelMate.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List; 

import jakarta.persistence.CascadeType; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "trips") 
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(nullable = false) 
    private String title; 

    @Column(nullable = false) 
    private LocalDate startDate; 

    @Column(nullable = false) 
    private LocalDate endDate; 

    @Column(nullable = false) 
    private BigDecimal budget; 

    private BigDecimal totalExpense; 

    @Column(nullable = false) 
    private String category = "Leisure"; 

    @ManyToOne 
    @JoinColumn(name = "traveler_id") 
    private User traveler; 

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL) 
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("trip") 
    private List<Itinerary> itineraries; 

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL) 
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("trip") 
    private List<Expense> expenses; 

    
    public Trip() {
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getCategory() { 
        return category; 
    }

    public void setCategory(String category) { 
        this.category = category; 
    }

    public User getTraveler() {
        return traveler;
    }

    public void setTraveler(User traveler) {
        this.traveler = traveler;
    }
}