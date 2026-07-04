package com.Sprout.app.Entity;


import jakarta.persistence.*;

@Entity
public class Crops {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSoilType() {
	return soilType;
}
public void setSoilType(String soilType) {
	this.soilType = soilType;
}
public String getClimate() {
	return climate;
}
public void setClimate(String climate) {
	this.climate = climate;
}
public String getSeedRate() {
	return seedRate;
}
public void setSeedRate(String seedRate) {
	this.seedRate = seedRate;
}
public String getCropDuration() {
	return cropDuration;
}
public void setCropDuration(String cropDuration) {
	this.cropDuration = cropDuration;
}
public String getProductivity() {
	return productivity;
}
public void setProductivity(String productivity) {
	this.productivity = productivity;
}
public String getFeatures() {
	return features;
}
public void setFeatures(String features) {
	this.features = features;
}
private String soilType;
 private String climate;
 private String seedRate;
 private String cropDuration;
 private String productivity;
 private String features;
 
}

