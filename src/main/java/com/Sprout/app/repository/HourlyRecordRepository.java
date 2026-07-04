package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.HourlyRecord;

public interface HourlyRecordRepository extends JpaRepository<HourlyRecord, Long> {
}

