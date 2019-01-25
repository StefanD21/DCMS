package com.DCMS;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long driverId;

	private long driverCallsign;
	private String driverName;
	private String driverNotes;
	private long timeSinceLastJob;
	
	public Driver() {}
	
	public Driver(long driverId, long driverCallsign,long timeSinceLastJob, String driverName, String driverNotes) {
		this.driverId = driverId;
		this.driverCallsign = driverCallsign;
		this.driverName = driverName;
		this.timeSinceLastJob = timeSinceLastJob;
		this.driverNotes = driverNotes;
	}

	public long getTimeSinceLastJob() {
		return timeSinceLastJob;
	}

	public void setTimeSinceLastJob(long timeSinceLastJob) {
		this.timeSinceLastJob = timeSinceLastJob;
	}

	public long getDriverId() {
		return driverId;
	}

	public void setDriverId(long driverId) {
		this.driverId = driverId;
	}

	public long getDriverCallsign() {
		return driverCallsign;
	}

	public void setDriverCallsign(long driverCallsign) {
		this.driverCallsign = driverCallsign;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNotes() {
		return driverNotes;
	}

	public void setDriverNotes(String driverNotes) {
		this.driverNotes = driverNotes;
	}
	
}
