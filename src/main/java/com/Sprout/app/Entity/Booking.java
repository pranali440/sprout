package com.Sprout.app.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    public Integer bookingId;

    @Column(name = "booking_date")
    public LocalDate bookingDate;
    
    @Column(name = "booking_time")
    public LocalTime bookingTime;

    public LocalTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	@ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "tractor_id")
    private Tractor tractor;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;


    public Booking() {
    }
    
    @Column(name = "status") 
    private String status ="pending";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate date) {
		this.bookingDate = date;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Tractor getTractor() {
		return tractor;
	}

	public void setTractor(Tractor tractor) {
		this.tractor = tractor;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}

