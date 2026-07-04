package com.Sprout.app.Entity;


import jakarta.persistence.*;

@Entity
public class HourlyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "`load`")
    private int load;
    @Column(name = "current")
    private int current;
    @Column(name = "voltage")
    private int voltage;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getLoad() {
		return load;
	}
	public void setLoad(int load) {
		this.load = load;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getVoltage() {
		return voltage;
	}
	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}


}
