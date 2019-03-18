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

import com.DCMS.Controllers.ClearListController;
import com.DCMS.Controllers.DriverController;
import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Entities.Job;
import com.DCMS.Repositories.ClearListRepository;
import com.DCMS.Repositories.DriverRepository;
import com.DCMS.Repositories.JobRepository;

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
		
		// add list to repository and change to active list
		clearLists.add(new ClearList ("Roaders List", q, false));
		clearListRepository.saveAll(clearLists);
		ClearListController.changeActiveList(1);
		ClearList list = ClearListController.getActiveList();
		
		//creates an array of drivers and adds them to repository
		Driver driver1 = (new Driver (10, "Mike", "Dog Car"));
		Driver driver2 = (new Driver (23, "Phil", "Wheelchair Car"));
		Driver driver3 = (new Driver (40, "Steve", "Standard Car"));
		drivers.add(driver1);
		drivers.add(driver2);
		drivers.add(driver3);
		driverRepository.saveAll(drivers);
		
		Job j1 = new Job();
		j1.setDriver(driver1);
		j1.setJobNotes("no notes");
		j1.setOperatorName("stefan");
		j1.setJobReference(9763);
		j1.setDate(d1);
		j1.setClearList(list);
		
		Job j2 = new Job();
		j2.setDriver(driver2);
		j2.setJobNotes("no notes");
		j2.setOperatorName("alex");
		j2.setJobReference(90987);
		j2.setDate(d2);
		j2.setClearList(list);
		
		Job j3 = new Job();
		j3.setDriver(driver3);
		j3.setJobNotes("no notes");
		j3.setOperatorName("ben");
		j3.setJobReference(9754);
		j3.setDate(d3);
		j3.setClearList(list);
		
		jobs.add(j1);
		jobs.add(j2);
		jobs.add(j3);
		
		jobRepository.saveAll(jobs); 
	}
	
}
