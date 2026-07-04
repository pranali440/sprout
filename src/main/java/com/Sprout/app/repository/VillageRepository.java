package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
	Village findByName(String villageName);
}

