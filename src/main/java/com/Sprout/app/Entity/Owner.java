package com.Sprout.app.Entity;


import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "village_name")
    private String villageName;

    @Column(name = "contact_info")
    private String contactInfo;

    @OneToMany(mappedBy = "owner")
    private List<Tractor> tractors;

    @OneToMany(mappedBy = "owner")
    private List<Driver> drivers;
    
    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    

    public Integer getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getVillageName() {
		return villageName;
	}



	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}



	public String getContactInfo() {
		return contactInfo;
	}



	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}



	public List<Tractor> getTractors() {
		return tractors;
	}



	public void setTractors(List<Tractor> tractors) {
		this.tractors = tractors;
	}



	public List<Driver> getDrivers() {
		return drivers;
	}



	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}



	public Sector getSector() {
		return sector;
	}



	public void setSector(Sector sector) {
		this.sector = sector;
	}


	public Owner() {
    }
}

