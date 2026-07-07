package com.example.TravelMate.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelMate.entity.Route;
import com.example.TravelMate.service.RouteService;

@CrossOrigin(origins = "*", maxAge = 3600) 
@RestController 
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes(); 
    }

    @PostMapping
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public Route createRoute(@Valid @RequestBody Route route) {
        return routeService.createRoute(route); 
    }

    @GetMapping("/optimize")
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')") 
    public List<Route> getOptimizedRoute(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam String criteria) {
        return routeService.optimizeRoute(source, destination, criteria); 
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TRAVELER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }
}