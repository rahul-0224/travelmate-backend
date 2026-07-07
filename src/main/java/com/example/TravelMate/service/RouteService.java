package com.example.TravelMate.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.TravelMate.entity.Route;
import com.example.TravelMate.repository.RouteRepository;

@Service
public class RouteService {

    private final RouteRepository routeRepository; 

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll(); 
    }

    public Route createRoute(Route route) {
        if (route.getSource() != null) {
            route.setSource(route.getSource().trim());
        }
        if (route.getDestination() != null) {
            route.setDestination(route.getDestination().trim());
        }
        return routeRepository.save(route); 
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    public List<Route> optimizeRoute(String source, String destination, String criteria) {
        if (source == null || destination == null) {
            return List.of();
        }
        String searchSource = source.trim();
        String searchDestination = destination.trim();
        return routeRepository.findAll().stream()
                .filter(r -> r.getSource() != null && r.getDestination() != null)
                .filter(r -> r.getSource().trim().equalsIgnoreCase(searchSource) && r.getDestination().trim().equalsIgnoreCase(searchDestination))
                .sorted((r1, r2) -> {
                    if ("time".equalsIgnoreCase(criteria)) {
                        double t1 = r1.getEstimatedTravelTime() != null ? r1.getEstimatedTravelTime() : Double.MAX_VALUE;
                        double t2 = r2.getEstimatedTravelTime() != null ? r2.getEstimatedTravelTime() : Double.MAX_VALUE;
                        return Double.compare(t1, t2);
                    } else if ("distance".equalsIgnoreCase(criteria)) {
                        double d1 = r1.getDistance() != null ? r1.getDistance() : Double.MAX_VALUE;
                        double d2 = r2.getDistance() != null ? r2.getDistance() : Double.MAX_VALUE;
                        return Double.compare(d1, d2);
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }
}