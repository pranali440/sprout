package com.Sprout.app.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tractors")
public class Tractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tractor_id")
    private Integer tractorId;

    @Column(name = "name")
    private String name;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "is_allocated")
    private boolean isAllocated=false;

    public Integer getTractorId() {
		return tractorId;
	}

	public void setTractorId(Integer tractorId) {
		this.tractorId = tractorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean isAllocated() {
		return isAllocated;
	}

	public void setAllocated(boolean isAllocated) {
		this.isAllocated = isAllocated;
	}

	public Tractor() {
    }
}
