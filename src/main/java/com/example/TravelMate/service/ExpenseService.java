package com.example.TravelMate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TravelMate.entity.Expense;
import com.example.TravelMate.entity.Trip;
import com.example.TravelMate.repository.ExpenseRepository;
import com.example.TravelMate.repository.TripRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final TripRepository tripRepository;

    
    public ExpenseService(ExpenseRepository expenseRepository, TripRepository tripRepository) {
        this.expenseRepository = expenseRepository;
        this.tripRepository = tripRepository;
    }

    public List<Expense> getExpensesByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        return expenseRepository.findByTrip(trip);
    }

    public Expense addExpense(Long tripId, Expense expense) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        expense.setTrip(trip);
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}