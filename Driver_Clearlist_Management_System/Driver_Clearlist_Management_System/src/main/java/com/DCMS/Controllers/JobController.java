package com.DCMS.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Entities.Job;
import com.DCMS.Repositories.DriverRepository;
import com.DCMS.Repositories.JobRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;



@RestController
@RequestMapping (value = "/jobs")
public class JobController {
	Date cDate = new Date();
	
	static JobRepository jobRepository;
	DriverRepository driverRepository;
	
	@Autowired
	public JobController(JobRepository jobRepository, 
			DriverRepository driverRepository) {
		this.jobRepository = jobRepository;
		this.driverRepository = driverRepository;
	}
	
	public JobController() {}
	//create a job on the active list
	@RequestMapping(value = "/{jobReference}/{driverid}/{operatorname}/{jobnotes}/add", method = RequestMethod.POST)
	public String addJob(
				@RequestBody Job job, 
				@PathVariable long jobReference,
				@PathVariable long driverId,
				@PathVariable String operatorName,
				@PathVariable String jobNotes){
		if (driverRepository.existsById(driverId)) {
			ClearList alist = ClearListController.getActiveList();
			Driver driver = DriverController.getDriverById(driverId);
			job.setClearList(alist);
			job.setDriver(driver);
			job.setJobNotes(jobNotes);
			job.setJobReference(jobReference);
			job.setOperatorName(operatorName);
			
			jobRepository.save(job);
			return String.format("A Job has been successfully created in the list %s",alist);
		}
		
		else {
			return "The driver associated with this job does not exist";
		}
		
	}
	
	
	//deletes a clearlist by its given ID
	@RequestMapping(value = "/{jobId}/delete", method = RequestMethod.GET)
	public String deleteJob(@PathVariable long jobId){
		Job job = jobRepository.findByjobId(jobId);
		long jobRef = job.getJobReference();
		jobRepository.deleteById(jobId);
		
		return String.format("The job with reference number %s has been deleted", jobRef);
	}
	
	//returns a list of jobs
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Job> getAllJobs(){
		
		return jobRepository.findAll();
	}
	
	//returns a job by its given ID
	@RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
	public Job getJobById(@PathVariable long jobId){
		
		return jobRepository.findByjobId(jobId);
	}
	
	//deletes a drivers last job
	@RequestMapping(value = "/{driverCallsign}/deletelast", method = RequestMethod.GET)
	public String deleteLastJob(@PathVariable long driverCallsign) {
		Job lastJob = DriverController.getLastJob(driverCallsign); 
		long jobId = lastJob.getJobId();
		
		return deleteJob(jobId);
	}
	
	// gets the lastjob in an active list
	@RequestMapping(value = "/last", method = RequestMethod.GET)
	public static Job getLastJobInClearlist() {
		ClearList list = ClearListController.getActiveList();
		
		return jobRepository.findTopByclearlist(list);
	}

}
