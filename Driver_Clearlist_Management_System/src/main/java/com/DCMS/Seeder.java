package com.DCMS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
	private JobRepository jobRepository;
	private DriverRepository driverRepository;
	private ClearListRepository clearListRepository;
	
	@Autowired
	public Seeder(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	//@Autowired
	//public Seeder(DriverRepository driverRepository) {
	//	this.driverRepository = driverRepository;
	//}
	
	//@Autowired
	//public Seeder(ClearListRepository clearListRepository) {
	//	this.clearListRepository = clearListRepository;
	//}
	
	@Override
	public void run(String... strings) throws Exception{
		List<Job> jobs = new ArrayList<>();
		List<Driver> drivers = new ArrayList<>();
		List<ClearList> clearLists = new ArrayList<>();
		
		jobs.add(new Job(100, 21, "stefan", "no notes",1));
		jobs.add(new Job(120, 11, "stefan", "some notes",1));
		jobs.add(new Job(90, 5, "stefan", "no notes",2));
		
		//drivers.add(new Driver (1, 10, "Mike", "Dog Car"));
		//drivers.add(new Driver (2, 23, "Phil", "Wheelchair Car"));
		//drivers.add(new Driver (3, 40, "Steve", "Standard Car"));
		
		//clearLists.add(new ClearList (1 , "Roaders List"));
		//clearLists.add(new ClearList(2, "Bus List"));
		
		jobRepository.saveAll(jobs);
		//driverRepository.saveAll(drivers);
		//clearListRepository.saveAll(clearLists);
	}
	
}
