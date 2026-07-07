package com.example.TravelMate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TravelMate.entity.TravelPackage;
import com.example.TravelMate.entity.User;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
   
    List<TravelPackage> findByGuide(User guide); 
}