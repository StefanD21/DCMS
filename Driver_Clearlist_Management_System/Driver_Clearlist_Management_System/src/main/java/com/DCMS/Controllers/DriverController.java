package com.DCMS.Controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Entities.Job;
import com.DCMS.Repositories.ClearListRepository;
import com.DCMS.Repositories.DriverRepository;
import com.DCMS.Repositories.JobRepository;

@RestController
@RequestMapping (value = "/drivers")
public class DriverController {
	 
	static DriverRepository driverRepository;
	static JobRepository jobRepository;
	 
	 @Autowired
	 public DriverController(DriverRepository driverRepository, JobRepository jobRepository) {
		 this.driverRepository = driverRepository;
		 this.jobRepository = jobRepository;
	 }
	 
	 public DriverController() {}
	 
	 @RequestMapping(value = "/{driverCallsign}/{driverName}/{driverNotes}/add", method = RequestMethod.POST)
		public String addDriver(
				
				@PathVariable long driverCallsign,
				@PathVariable String driverName,
				@PathVariable String driverNotes){
		 Driver driver = new Driver();
		driver.setDriverCallsign(driverCallsign);;
		driver.setDriverName(driverName);
		driver.setDriverNotes(driverNotes);
		 
		 driverRepository.save(driver);
			
			return "A driver has been successfully created";
		}
		
		//deletes a driver by its given ID
		@RequestMapping(value = "/{driverId}/delete", method = RequestMethod.DELETE)
		public String deleteDriver(@PathVariable long driverId){
			Driver driver = driverRepository.findBydriverId(driverId);
			long callsign = driver.getDriverCallsign();
			driverRepository.deleteById(driverId);
			
			return String.format("Driver with callsign %s has been deleted",callsign);
		}
		
		//returns a list of drivers
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public List<Driver> getAllDrivers(){
			
			return driverRepository.findAll();
		}
		
		//returns a driver by its given ID
		@RequestMapping(value = "/{driverId}", method = RequestMethod.GET)
		public static Driver getDriverById(@PathVariable long driverId){
			
			return driverRepository.findBydriverId(driverId);
		}
		
		//returns a list of jobs where the given driver ID exists
		@RequestMapping(value = "/{driverId}/jobs", method = RequestMethod.GET)
		public List<Job> getDriverJobs(@PathVariable long driverId){
			Driver driver = DriverController.getDriverById(driverId);
			
			return jobRepository.findByDriver(driver);
		}
		
		//returns the last job where the given driver ID exists
		@RequestMapping(value = "/{driverId}/lastjob", method = RequestMethod.GET)
		public static Job getLastJob(@PathVariable long driverId) {
			Driver driver = DriverController.getDriverById(driverId);
			
			return jobRepository.findTopByDriverOrderByDateDesc(driver);
			
		}
		
		//returns the last job where the given driver ID exists in the active list
		//fiixxxx must return only job in thr active list!!!!
		@RequestMapping(value = "/{driverId}/lastjobinactive", method = RequestMethod.GET)
		public static Job getLastJobInClearlist(@PathVariable long driverId) {
			ClearList list = ClearListController.getActiveList();
			Driver driver = DriverController.getDriverById(driverId);
			
			return jobRepository.findTopByclearlistAndDriverOrderByDateDesc(list, driver);
		}
		
		
	 
}
