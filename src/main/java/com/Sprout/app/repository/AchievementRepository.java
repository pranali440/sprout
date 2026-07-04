package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    
}

