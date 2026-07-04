package com.Sprout.app.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleForm {
    private Integer bookingId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Integer farmerId;
    private Integer ownerId;
    private Integer tractorId;
    private Integer driverId;
    private Integer numberOfTrolleys;

    
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getTractorId() {
        return tractorId;
    }

    public void setTractorId(Integer tractorId) {
        this.tractorId = tractorId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getNumberOfTrolleys() {
        return numberOfTrolleys;
    }

    public void setNumberOfTrolleys(Integer numberOfTrolleys) {
        this.numberOfTrolleys = numberOfTrolleys;
    }
}

