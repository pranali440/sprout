package com.Sprout.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.Sprout.app.Entity.Owner;
import com.Sprout.app.Entity.Sector;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByVillageName(String villageName);
    List<Owner> findBySector(Sector sector);
    Optional<Owner> findById(Long ownerId);
}
