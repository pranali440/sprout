package com.Sprout.app.Entity;

import jakarta.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long scheduleId;

    private Long farmerId;

    private double trolley1Weight;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Long getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(Long farmerId) {
		this.farmerId = farmerId;
	}

	public double getTrolley1Weight() {
		return trolley1Weight;
	}

	public void setTrolley1Weight(double trolley1Weight) {
		this.trolley1Weight = trolley1Weight;
	}

	public double getTrolley2Weight() {
		return trolley2Weight;
	}

	public void setTrolley2Weight(double trolley2Weight) {
		this.trolley2Weight = trolley2Weight;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	private double trolley2Weight;

    private double totalWeight;

}
