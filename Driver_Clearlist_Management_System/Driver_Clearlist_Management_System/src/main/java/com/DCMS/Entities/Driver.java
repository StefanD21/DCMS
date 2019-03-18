package com.DCMS.Entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Driver")
@Table(name = "drivers")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "driver_id")
	private long driverId;
	@NaturalId
	private long driverCallsign;
	private String driverName;
	private String driverNotes;
	
	@ManyToMany(mappedBy="queue")
	private List<ClearList> clearlists = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "driver")
	private List <Job> jobs = new ArrayList<>();
	

	public Driver() {}
	
	public Driver(long driverCallsign, String driverName, String driverNotes) {
		this.driverCallsign = driverCallsign;
		this.driverName = driverName;
		this.driverNotes = driverNotes;
	}

	
	@JsonIgnore
	public List<ClearList> getClearlists() {
		return clearlists;
	}

	public void setClearlists(List<ClearList> clearlists) {
		this.clearlists = clearlists;
	}
	@JsonIgnore
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
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
