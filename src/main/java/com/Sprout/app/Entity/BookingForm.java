package com.Sprout.app.Entity;

public class BookingForm {
    private Long bookingId;
    private Farmer farmer;
    public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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
	private Owner owner;
    private Tractor tractor;
    private Driver driver;

}
