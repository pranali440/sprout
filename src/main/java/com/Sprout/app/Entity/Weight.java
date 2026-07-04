package com.Sprout.app.Entity;



	import jakarta.persistence.*;

	@Entity
	public class Weight {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private Integer scheduleId;

	    private Integer farmerId;

	    private double trolley1Weight;

	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getScheduleId() {
			return scheduleId;
		}

		public void setScheduleId(Integer scheduleId) {
			this.scheduleId = scheduleId;
		}

		public Integer getFarmerId() {
			return farmerId;
		}

		public void setFarmerId(Integer farmerId) {
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


