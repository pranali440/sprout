package com.Sprout.app.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Sprout.app.Entity.Booking;
import com.Sprout.app.Entity.Driver;
import com.Sprout.app.Entity.Tractor;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
	@Query("SELECT DISTINCT b.driver FROM Booking b WHERE b.driver IS NOT NULL")
    List<Driver> findBookingsWithAllocatedDrivers();

    @Query("SELECT DISTINCT b.tractor FROM Booking b WHERE b.tractor IS NOT NULL")
    List<Tractor> findBookingsWithAllocatedTractors();
    
    List<Booking> findByBookingDate(LocalDate bookingDate);
    
    
    
}
