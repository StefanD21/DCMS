package com.DCMS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
