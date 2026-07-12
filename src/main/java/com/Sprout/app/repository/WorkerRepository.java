package com.Sprout.app.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Worker;



@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Worker findByEmail(String email); 
    Optional<Worker> findById(Integer workerId);
    java.util.List<Worker> findByApprovedFalse();
}