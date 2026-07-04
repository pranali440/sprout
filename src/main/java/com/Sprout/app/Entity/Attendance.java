package com.Sprout.app.Entity;

import java.time.LocalDate;

public class Attendance {
    private Long id;
    private LocalDate date;
    private String workerId;
    private String workerName;
    private String status;
	
    public Attendance(Long id, LocalDate date, String workerId, String workerName, String status) {
        this.id = id;
        this.date = date;
        this.workerId = workerId;
        this.workerName = workerName;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}    
}
