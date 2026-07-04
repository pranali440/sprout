package com.Sprout.app.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "todays_bookings")
public class TodaysBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "farmer_id")
    private Long farmerId;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "farmer_name")
    private String farmerName;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "booking_time")
    private LocalTime bookingTime;

    @Column(name = "location")
    private String location;
    
    @Column(name = "status") 
    private String status ="pending";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 

}

