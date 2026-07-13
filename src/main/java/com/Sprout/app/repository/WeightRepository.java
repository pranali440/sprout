package com.Sprout.app.repository;

import java.util.List;

import com.Sprout.app.Entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer> {
    List<Weight> findByFarmerId(Integer farmerId);
}