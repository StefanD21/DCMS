package com.DCMS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.util.JavaScriptUtils;

@Component
public class Seeder implements CommandLineRunner {
	private JobRepository jobRepository;
	private DriverRepository driverRepository;
	private ClearListRepository clearListRepository;
	
	@Autowired
	public Seeder(DriverRepository driverRepository, 
			JobRepository jobRepository,
			ClearListRepository clearListRepository) {
		this.driverRepository = driverRepository;
		this.jobRepository = jobRepository;
		this.clearListRepository = clearListRepository;
	}
	
	
	@Override
	public void run(String... strings) throws Exception{
		List<Job> jobs = new ArrayList<>();
		List<Driver> drivers = new ArrayList<>();
		List<ClearList> clearLists = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date d4 = new Date();
		Date d1 = format.parse("06/24/2017");
		Date d2 = format.parse("06/30/2017");
		Date d3 = format.parse("07/32/2017");
		ArrayList<Driver> q = new ArrayList<>();
		
		
		jobs.add(new Job(0, 100, 21, "stefan", "no notes",1, d1));
		jobs.add(new Job(0, 120, 11, "stefan", "some notes",1, d2));
		jobs.add(new Job(0, 90, 5, "stefan", "no notes", 2, d3 ));
		jobs.add(new Job(0, 1000980, 21, "Ben", "No Notes", 1, d4));
		
		drivers.add(new Driver (1, 10 ,"Mike", "Dog Car"));
		drivers.add(new Driver (2, 23, "Phil", "Wheelchair Car"));
		drivers.add(new Driver (3, 40, "Steve", "Standard Car"));
		
		//q.add(new Driver(10, 99, "stefan", "pleasework"));
		clearLists.add(new ClearList (1 , "Roaders List", q));
		
		
		
		
		jobRepository.saveAll(jobs);
		driverRepository.saveAll(drivers);
		clearListRepository.saveAll(clearLists);
	}
	
}
