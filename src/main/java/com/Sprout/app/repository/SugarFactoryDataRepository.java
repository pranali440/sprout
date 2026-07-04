package com.Sprout.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.SugarFactoryData;

public interface SugarFactoryDataRepository extends JpaRepository<SugarFactoryData, Long> {
	List<SugarFactoryData> findBySeason(String season);
}

