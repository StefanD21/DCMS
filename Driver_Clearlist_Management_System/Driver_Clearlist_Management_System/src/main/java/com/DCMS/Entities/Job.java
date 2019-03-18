package com.DCMS.Entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity(name = "Job")
@Table(name = "jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	
	private long jobReference;
	private String operatorName;
	private String jobNotes;
	private Date date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "clearlist_id")
	private ClearList clearlist;
	
	public Job() {}
	
	public Job(long jobReference, Driver driver, String operatorName, String jobNotes, Date date) {
		this.jobReference = jobReference;
		this.driver = driver;
		this.operatorName = operatorName;
		this.jobNotes = jobNotes;
		if(date == null) {
			this.date = new Date();
		}
		else {
			this.date = date;
		}
		
	}
	
	
	//getters and setters
	
	
	public long getJobId() {
		return jobId;
	}
	
	public long getJobReference() {
		return jobReference;
	}
	
	public Date getDate() {
		return date;
	}

	@JsonIgnore
	public ClearList getClearList() {
		return clearlist;
	}
	
	
	public void setDate(Date date) {
		if (date == null) {
			this.date = new Date();
		}
		else {
			this.date = date;
		}
	}
	
	public void setJobReference(long jobReference) {
		this.jobReference = jobReference;
	}


	public void setClearList(ClearList clearlist) {
		this.clearlist = clearlist;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	@JsonIgnore
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getJobNotes() {
		return jobNotes;
	}
	
	public void setJobNotes(String jobNotes) {
		this.jobNotes = jobNotes;
	}
	
	
	

}
