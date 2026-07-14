package com.Sprout.app.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sprout.app.Entity.TodaysBooking;
import com.Sprout.app.repository.TodaysBookingRepository;

@Service
public class TodaysBookingService {
    @Autowired
    private TodaysBookingRepository todaysBookingRepository;

    public List<TodaysBooking> getTodaysBookings(LocalDate bookingDate) {
        return todaysBookingRepository.findByBookingDate(bookingDate);
    }
    
    public TodaysBooking saveTodaysBooking(TodaysBooking todaysBooking) {
        return todaysBookingRepository.save(todaysBooking);
    }
    
    public void update(TodaysBooking todaysBooking) {
        todaysBookingRepository.save(todaysBooking);
    }
    
    public TodaysBooking findById(Long id) {
        return todaysBookingRepository.findById(id).orElse(null);
    }

    public List<TodaysBooking> getBookingsByFarmerId(Long farmerId) {
        return todaysBookingRepository.findByFarmerIdOrderByBookingDateDesc(farmerId);
    }
}