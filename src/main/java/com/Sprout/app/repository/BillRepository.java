package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
}
