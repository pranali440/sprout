package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.FertilizerData;

public interface FertilizerDataRepository extends JpaRepository<FertilizerData, Long> {
    
}

