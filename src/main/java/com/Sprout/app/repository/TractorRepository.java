package com.Sprout.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Tractor;


@Repository
public interface TractorRepository extends JpaRepository<Tractor, Integer> {
	List<Tractor> findByOwner(Owner owner);
	Optional<Tractor> findByName(String name);
	Optional<Tractor> findById(Integer Id);
}


