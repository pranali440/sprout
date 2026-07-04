package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}