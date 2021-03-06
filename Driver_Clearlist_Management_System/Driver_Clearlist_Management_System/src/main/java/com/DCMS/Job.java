package com.DCMS;

import javax.persistence.Id;

public class Job {
	@Id
	private long jobId;
	
	private long driverCallsign;
	private String operatorName;
	private String jobNotes;
	
	public Job() {}
	
	public Job(long jobId, long driverCallsign, String operatorName, String jobNotes) {
		this.jobId = jobId;
		this.driverCallsign = driverCallsign;
		this.operatorName = operatorName;
		this.jobNotes = jobNotes;
	}
	
	//getters and setters
	public long getJobId() {
		return jobId;
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
