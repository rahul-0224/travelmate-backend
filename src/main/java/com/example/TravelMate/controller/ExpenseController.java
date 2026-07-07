package com.example.TravelMate.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.entity.Expense;
import com.example.TravelMate.service.ExpenseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/trips/{tripId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')")
    public List<Expense> getExpenses(@PathVariable Long tripId) {
        return expenseService.getExpensesByTrip(tripId);
    }

    @PostMapping
    @PreAuthorize("hasRole('TRAVELER')")
    public Expense addExpense(@PathVariable Long tripId, @RequestBody Expense expense) {
        return expenseService.addExpense(tripId, expense);
    }
}