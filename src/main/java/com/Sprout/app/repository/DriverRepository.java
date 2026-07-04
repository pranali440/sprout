package com.Sprout.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Owner;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	List<Driver> findByOwner(Owner owner);
	Optional<Driver> findByName(String name);
	     Optional<Driver> findById(Integer DriverId);
}
