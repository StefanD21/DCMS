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
	private static DriverRepository driverRepository;
	private static ClearListRepository clearListRepository;
	
	@Autowired
	public Services(ClearListRepository clearListRepository,
			DriverRepository driverRepository,
			JobRepository jobRepository) {
		this.clearListRepository = clearListRepository;
		this.driverRepository = driverRepository;
		this.jobRepository = jobRepository;
	}
	
	
	//Clearlist Services -----------------------------------------------------
	//adds a clearlist
	@RequestMapping(value = "/addclearlist", method = RequestMethod.POST)
	public String addList(@RequestBody ClearList clearList){
		clearListRepository.save(clearList);
		
		return "A Clearlist has been successfully created";
	}
	
	//deletes a clearlist by its given ID
	@RequestMapping(value = "/deletelist/{listId}", method = RequestMethod.GET)
	public String deleteList(@PathVariable long listId){
		ClearList list = clearListRepository.findById(listId).get();
		String listN = list.getListName();
		clearListRepository.deleteById(listId);
		
		return String.format("The Clearlist %s has been deleted",listN);
	}
	
	//returns a list of clearlists
	@RequestMapping(value = "/getallclearlists", method = RequestMethod.GET)
	public static List<ClearList> getAllClearlists(){
		
		return clearListRepository.findAll();
	}
	
	//returns a list given by its ID 
	@RequestMapping(value = "/getclearlistbyid/{listId}", method = RequestMethod.GET)
	public static ClearList getClearlistById(@PathVariable long listId){
		
		return clearListRepository.findBylistId(listId);
	}
	
	//returns a clearlists queue
	@RequestMapping(value = "/getqueue/{listId}", method = RequestMethod.GET)
	public List<Driver> getQueue(@PathVariable long listId){
		ClearList cList = Services.getClearlistById(listId);
		
		return cList.getQueue();
	}
	
	
	//Job mappings ----------------------------------------------------------------------------
	//adds a job
	@RequestMapping(value = "/addjob", method = RequestMethod.POST)
	public String addJob(@RequestBody Job job){
		jobRepository.save(job);
		
		return "A Job has been successfully created";
	}
	
	//deletes a clearlist by its given ID
	@RequestMapping(value = "/deletejob/{jobId}", method = RequestMethod.GET)
	public String deleteJob(@PathVariable long jobId){
		Job job = jobRepository.findByjobId(jobId);
		long jobRef = job.getJobReference();
		jobRepository.deleteById(jobId);
		
		return String.format("The job with reference number %s has been deleted", jobRef);
	}
	
	//returns a list of jobs
	@RequestMapping(value = "/getalljobs", method = RequestMethod.GET)
	public List<Job> getAllJobs(){
		
		return jobRepository.findAll();
	}
	
	//returns a job by its given ID
	@RequestMapping(value = "/getjobbyid/{jobId}", method = RequestMethod.GET)
	public Job getJobById(@PathVariable long jobId){
		
		return jobRepository.findByjobId(jobId);
	}
	
	
	//driver mappings ------------------------------------------------------------------------
	//adds a driver
	@RequestMapping(value = "/adddriver", method = RequestMethod.POST)
	public String addDriver(@RequestBody Driver driver){
		driverRepository.save(driver);
		
		return "A driver has been successfully created";
	}
	
	//deletes a driver by its given ID
	@RequestMapping(value = "/deletedriver/{driverId}", method = RequestMethod.GET)
	public String deleteDriver(@PathVariable long driverId){
		Driver driver = driverRepository.findBydriverId(driverId);
		long callsign = driver.getDriverCallsign();
		driverRepository.deleteById(driverId);
		
		return String.format("Driver with callsign %s has been deleted",callsign);
	}
	
	//returns a list of drivers
	@RequestMapping(value = "/getalldrivers", method = RequestMethod.GET)
	public List<Driver> getAllDrivers(){
		
		return driverRepository.findAll();
	}
	
	//returns a driver by its given ID
	@RequestMapping(value = "/getdriverbyid/{driverId}", method = RequestMethod.GET)
	public static Driver getDriverById(@PathVariable long driverId){
		
		return driverRepository.findBydriverId(driverId);
	}
	
	//returns a list of jobs where the given driver ID exists
	@RequestMapping(value = "/driverjobs/{drivercallsign}", method = RequestMethod.GET)
	public List<Job> getDriverJobs(@PathVariable long DriverCallsign){
		
		return jobRepository.findBydriverCallsign(DriverCallsign);
	}
	
	//returns the last job where the given driver ID exists
	@RequestMapping(value = "/getdriverlastjob/{drivercallsign}", method = RequestMethod.GET)
	public Job getDriverLastJob(@PathVariable  long DriverCallsign) {
		
		return jobRepository.findTopByDriverCallsignOrderByDateDesc(DriverCallsign);
		
	}
	
// General Services --------------------------------------------------------------------------------	
	
	public void sortList(long listId) {
		//sort list by timesincelastjob
	}
	
	// adds a given driver to a given clearlist + creates a dummy job to hold drivers position in queue
	@RequestMapping(value = "/adddrivertolist/{listId}/{driverId}", method = RequestMethod.GET)
	public ArrayList<Driver> addDriverToClearList(
			@PathVariable("listId") long listId, 
			@PathVariable("driverId") long driverId) {
		Driver driver = new Driver();
		ClearList list = new ClearList();
		//List<Driver> cqueue = new ArrayList<>(queue);
		
		list = Services.getClearlistById(listId);
		driver = Services.getDriverById(driverId);
		Date cDate = new Date();
		List<Driver> queue = list.getQueue();
		ArrayList<Driver> cqueue = new ArrayList<>(queue);
		//cqueue.add(driver);
		list.setQueue(cqueue);
		Job placeholder = new Job(0, 0, driverId, "SYSTEM", "CLEARLIST PLACE HOLDER JOB",listId, cDate);
		jobRepository.save(placeholder);
		//clearListRepository.save(list);
		long callsign = driver.getDriverCallsign();
		String listn = list.getListName();
		
		return cqueue;
		//return String.format("Driver %s has been added to the clearlist %s", callsign, listn);
	} 
	
	//removes a given driver from a given list
	public void removeDriverFromList(long clearListId, long driverId) {
		
	}
	
	//creates a job to be given to a specified driver ____--- not needed as addJob.POST does same thing
	/*@RequestMapping(value = "/givedriverjob/{listid}/{driverId}/{jobreference}/{operatorname}/{jobnotes}", method = RequestMethod.GET)
	public String giveDriverJob(
			@PathVariable ("listId") long listId,
			@PathVariable ("driverId") long driverId,
			@PathVariable ("jobReference") long jobReference,
			@PathVariable ("operatorName") String operatorName,
			@PathVariable ("jobNotes") String jobNotes){
		Date date = new Date();
		Driver driver = Services.getDriverById(driverId);
		long callsign = driver.getDriverCallsign(); 
		Job job = new Job(0, jobReference, callsign, operatorName, jobNotes, listId, date );
		jobRepository.save(job);
		
		return String.format("Job with the reference %s has been allocated to driver %s",jobReference, callsign);
	} */

}
