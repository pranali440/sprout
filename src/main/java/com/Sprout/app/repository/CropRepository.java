package com.Sprout.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.Crops;

public interface CropRepository extends JpaRepository<Crops, Long> {
}

