package com.DCMS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
	private JobRepository jobRepository;
	private DriverRepository driverRepository;
	private ClearListRepository clearlistRepository;
	
	public Seeder(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	public Seeder(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}
	
	public Seeder(ClearListRepository clearListRepository) {
		this.clearlistRepository = clearListRepository;
	}
	
	@Override
	public void run(String... strings) throws Exception{
		List<Job> jobs = new ArrayList<>();
		List<Driver> drivers = new ArrayList<>();
		List<ClearList> clearlists = new ArrayList<>();
		
		jobs.add(new Job(100, 21, "stefan", "no notes"));
		jobs.add(new Job(120, 11, "stefan", "some notes"));
		jobs.add(new Job(90, 5, "stefan", "no notes"));
		
		drivers.add(new Driver (1, 10, "Mike", "Dog Car"));
		drivers.add(new Driver (2, 23, "Phil", "Wheelchair Car"));
		drivers.add(new Driver (3, 40, "Steve", "Standard Car"));
		
		jobRepository.saveAll(jobs);
		driverRepository.saveAll(drivers);
	}
	
}
