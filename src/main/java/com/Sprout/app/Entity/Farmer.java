package com.Sprout.app.Entity;

import jakarta.persistence.*;

@Entity
	@Table(name = "farmers")
	public class Farmer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "farmer_id", length = 4) // Ensure 4-digit ID
	    private Integer farmerId;

	    public Integer getFarmerId() {
			return farmerId;
		}

		public void setFarmerId(Integer farmerId) {
			this.farmerId = farmerId;
		}

		@Column(name = "name")
	    private String name;

	    @Column(name = "email", nullable = false, unique = true) // Ensure unique email
	    private String email;

	    @Column(name = "phone")
	    private String phone;

	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public double getFieldOfCane() {
			return fieldOfCane;
		}

		public void setFieldOfCane(double fieldOfCane) {
			this.fieldOfCane = fieldOfCane;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		@Column(name = "fieldOfCane")
	    private double fieldOfCane=0.0;

	    @Column(name = "password")
	    private String password;

	   // @OneToOne(cascade = CascadeType.ALL)
	   @Column(name = "location")
	    public String location;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		 public Farmer() {
		    }
	    
	}