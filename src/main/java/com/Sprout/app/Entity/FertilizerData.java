package com.Sprout.app.Entity;

import jakarta.persistence.*;


@Entity
public class FertilizerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fertilizerType;
    private String description;
    private double nitrogen;
    private double phosphorus;
    private double potassium;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFertilizerType() {
		return fertilizerType;
	}
	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getNitrogen() {
		return nitrogen;
	}
	public void setNitrogen(double nitrogen) {
		this.nitrogen = nitrogen;
	}
	public double getPhosphorus() {
		return phosphorus;
	}
	public void setPhosphorus(double phosphorus) {
		this.phosphorus = phosphorus;
	}
	public double getPotassium() {
		return potassium;
	}
	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}

    
}

