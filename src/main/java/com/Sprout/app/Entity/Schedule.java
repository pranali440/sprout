package com.Sprout.app.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    public Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    public Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public Owner owner;

    @ManyToOne
    @JoinColumn(name = "tractor_id")
    public Tractor tractor;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    public Driver driver;
    
    @Column(name = "booking_Id")
    public Long bookingId;
    

    public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	@Column(name = "booking_date")
    public LocalDate bookingDate;

    @Column(name = "booking_time")
    public LocalTime bookingTime;

    @Column(name = "location")
    public String location;

    @Column(name = "number_of_trolleys")
    public Integer numberOfTrolleys;
    
    @Column(name="status")
    public String status="pending";

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Schedule() {
    }

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
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

	public Integer getNumberOfTrolleys() {
		return numberOfTrolleys;
	}

	public void setNumberOfTrolleys(Integer numberOfTrolleys) {
		this.numberOfTrolleys = numberOfTrolleys;
	}
 
    
    
}

