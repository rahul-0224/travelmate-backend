package com.example.TravelMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TravelMate.entity.Expense;
import com.example.TravelMate.entity.Trip;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
   
    List<Expense> findByTrip(Trip trip);
}