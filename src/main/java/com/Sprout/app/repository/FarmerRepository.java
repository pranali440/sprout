package com.Sprout.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Farmer;



@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
    Farmer findByEmail(String email); 
    Optional<Farmer> findById(Integer farmerId);
}
