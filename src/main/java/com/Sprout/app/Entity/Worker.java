package com.Sprout.app.Entity;

import jakarta.persistence.*;

@Entity
	@Table(name = "workers")
	public class Worker {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "worker_id", length = 4) // Ensure 4-digit ID
	    private Integer workerId;

	    public Integer getWorkerId() {
			return workerId;
		}

		public void setWorkerId(Integer workerId) {
			this.workerId = workerId;
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

		public String getDeptName() {
			return DeptName;
		}

		public void setDeptName(String DeptName) {
			this.DeptName = DeptName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getlocation() {
			return location;
		}

		public void setlocation(String location) {
			this.location = location;
		}

		@Column(name = "DeptName")
	    private String DeptName;

	    @Column(name = "password")
	    private String password;

	   // @OneToOne(cascade = CascadeType.ALL)
	   @Column(name = "location")
	    private String location;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

	    @Column(name = "approved")
	    private boolean approved = false;

	    public boolean isApproved() {
	        return approved;
	    }

	    public void setApproved(boolean approved) {
	        this.approved = approved;
	    }

		 public Worker() {
		    }

	}