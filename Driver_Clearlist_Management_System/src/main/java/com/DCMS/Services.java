package com.DCMS;

import java.lang.ProcessBuilder.Redirect;
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
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/services")
public class Services {
	private JobRepository jobRepository;
	private DriverRepository driverRepository;
	private ClearListRepository clearListRepository;
	
	@Autowired
	public Services(ClearListRepository clearListRepository,
			DriverRepository driverRepository,
			JobRepository jobRepository) {
		this.clearListRepository = clearListRepository;
		this.driverRepository = driverRepository;
		this.jobRepository = jobRepository;
	}
	

	public void sortList(long listId) {
		//sort list by timesincelastjob
	}
	
	@RequestMapping(value = "/adddrivertolist/{clearListId}/{driverId}", method = RequestMethod.GET)
	public String addDriverToClearList(@PathVariable("clearListId") long clearListId, @PathVariable("driverId") long driverId) {
		Driver driver = new Driver();
		ClearList list = new ClearList();
		ArrayList<Driver> queue = new ArrayList<>();
		
		list = ClearListController.getBylistId(clearListId);
		driver = DriverController.getBydriverId(driverId);
		Date cDate = new Date();
		queue = list.getQueue();
		queue.add(driver);
		list.setQueue(queue);
		Job placeholder = new Job(0, 0, driverId, "SYSTEM", "CLEARLIST PLACE HOLDER JOB",clearListId, cDate);
		jobRepository.save(placeholder);
		clearListRepository.save(list);
		long callsign = driver.getDriverCallsign();
		String listn = list.getListName();
		
		//return list.toString()+driver.toString()+"   "+queue.toString();
		return String.format("Driver %s has been added to the clearlist %s", callsign, listn);
	} 
	
	public void removeDriverClearList(ClearList clearListId, Driver driverId) {
		//remove driver from a specific clearList
	}
	
}
