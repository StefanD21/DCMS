package com.DCMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping (value = "/job")
public class JobController {
	
	JobRepository jobRepository;
	
	@Autowired
	public JobController(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	public JobController() {}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Job> getAll(){
		return jobRepository.findAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Job create(@RequestBody Job job){
		
		return jobRepository.save(job);
	}
	
	@RequestMapping(value = "/specifc", method = RequestMethod.GET)
	public Optional<Job> getSpecifc(@PathVariable long jobId){
		return jobRepository.findById(jobId);
	}
	
	@RequestMapping(value = "/delete/{jobId}", method = RequestMethod.GET)
	public List<Job> remove(@PathVariable long jobId){
		jobRepository.deleteById(jobId);
		
		return jobRepository.findAll();
	}
	
	@RequestMapping(value = "/driverjobs/{callsign}", method = RequestMethod.GET)
	public List<Job> getDriverJobs(@PathVariable long callsign){
		return jobRepository.findByDriverCallsign(callsign);
	}
	
	//@RequestMapping(value = "/lastjob/{callsign}", method = RequestMethod.GET)
	//public Job getLastJob(@PathVariable long callsign) {
	//	JobRepository.findByDriverCallsign(callsign);
	//}
}
