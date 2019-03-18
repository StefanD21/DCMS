package com.DCMS;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.DCMS.Controllers.ClearListController;
import com.DCMS.Controllers.DriverController;
import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Entities.Job;
import com.DCMS.Repositories.ClearListRepository;
import com.DCMS.Repositories.DriverRepository;
import com.DCMS.Repositories.JobRepository;

@RestController
@RequestMapping(value = "/services")
public class Services {
	private JobRepository jobRepository;
	private static DriverRepository driverRepository;
	private static ClearListRepository clearListRepository;
	private ClearList activeList = new ClearList();
	
	@Autowired
	public Services(ClearListRepository clearListRepository,
			DriverRepository driverRepository,
			JobRepository jobRepository) {
		this.clearListRepository = clearListRepository;
		this.driverRepository = driverRepository;
		this.jobRepository = jobRepository;
	}
	
	
// General Services --------------------------------------------------------------------------------	
	
	//sort list by timesincelastjob
	@RequestMapping(value = "/sortlist", method = RequestMethod.GET)
	public String sortList() {
		ClearList list = new ClearList();
		list = ClearListController.getActiveList();
		List<Driver> queue = list.getQueue();
		String listn = list.getListName();
		
		Collections.sort(queue, new Comparator<Driver>() {
			public int compare (Driver d1, Driver d2) {
				
				return Long.compare(DriverController.getLastJobInClearlist(d1.getDriverCallsign()).getDate().getTime(),
						DriverController.getLastJobInClearlist(d2.getDriverCallsign()).getDate().getTime());
			}
		});
		list.setQueue(queue);
		this.clearListRepository.save(list);
		
		return String.format("The clearlist %s has been sorted",listn); 
		
	}
	
	// adds a given driver to a given clearlist + creates a dummy job to hold drivers position in queue
	@RequestMapping(value = "/{listId}/{driverId}/add", method = RequestMethod.GET)
	public String addDriverToClearList(
			@PathVariable("listId") long listId, 
			@PathVariable("driverId") long driverId) {
		Driver driver = new Driver();
		ClearList list = new ClearList();
		List<Driver> cqueue = new ArrayList<>();
		
		list = ClearListController.getClearlistById(listId);
		driver = DriverController.getDriverById(driverId);
		Date cDate = new Date();
		cqueue = list.getQueue();
		long callsign = driver.getDriverCallsign();
		String listn = list.getListName();
		if(cqueue.contains(driver)) {
			return String.format("Driver %s is already in the selected clearlist", callsign);
		}
		else {
			cqueue.add(driver);
			list.setQueue(cqueue);
			Job placeholder = new Job(0, driver, "SYSTEM", "CLEARLIST PLACE HOLDER JOB", cDate);
			placeholder.setClearList(list);
			jobRepository.save(placeholder);
			clearListRepository.save(list);
		
			return String.format("Driver %s has been added to the selected clearlist %s", callsign, listn);
		}
	} 
	
	//removes a driver from the active clearlist 
	@RequestMapping(value = "/removedriverfromclearlist/{driverId}")
	public String removeDriverFromClearList(
	@PathVariable("driverId") long driverId) {
		Driver driver = new Driver();
		ClearList list = ClearListController.getActiveList();
		List<Driver> cqueue = new ArrayList<>();
		long callsign = driver.getDriverCallsign();
		String listn = list.getListName();

		driver = DriverController.getDriverById(driverId);
		cqueue = list.getQueue();
		if(cqueue.contains(driver) == false) {
			
			return String.format("A driver with the callsign %s is not in the selected clearlist",callsign);
		}
		else {
			int index = cqueue.indexOf(driver);
			cqueue.remove(index);
			clearListRepository.save(list);
			
			return String.format("Driver %s has successfully been removed to the clearlist %s", callsign, listn);
		}
	}
	
	
	

}
