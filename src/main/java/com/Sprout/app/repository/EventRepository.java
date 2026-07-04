package com.Sprout.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Sprout.app.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
   
}

