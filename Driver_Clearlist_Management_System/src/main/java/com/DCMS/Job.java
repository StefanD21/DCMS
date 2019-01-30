package com.DCMS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long jobId;
	
	private long jobReference;
	private long driverCallsign;
	private String operatorName;
	private String jobNotes;
	private long clearListId;
	private Date date;
	
	public Job() {}
	
	public Job(long jobId, long jobReference, long driverCallsign, String operatorName, String jobNotes, long clearListId, Date date) {
		this.jobId = jobId;
		this.jobReference = 
		this.driverCallsign = driverCallsign;
		this.operatorName = operatorName;
		this.jobNotes = jobNotes;
		this.clearListId = clearListId;
		this.date = date;
		
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


	public long getClearListId() {
		return clearListId;
	}
	
	
	public void setDateByDate(Date date) {
		this.date = date;
	}
	
	public void setJobReference(long jobReference) {
		this.jobReference = jobReference;
	}


	public void setClearListId(long clearListId) {
		this.clearListId = clearListId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	public long getDriverCallsign() {
		return driverCallsign;
	}
	
	public void setDriverCallsign(long driverCallsign) {
		this.driverCallsign = driverCallsign;
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
