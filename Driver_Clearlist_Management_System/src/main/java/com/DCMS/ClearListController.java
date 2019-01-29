package com.DCMS;

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


@RestController
@RequestMapping (value = "/clearlist")
public class ClearListController {

	static ClearListRepository clearListRepository;
	static DriverRepository driverRepository;
	
	
	@Autowired
	public ClearListController(ClearListRepository clearListRepository,
			DriverRepository driverRepository) {
		this.clearListRepository = clearListRepository;
		this.driverRepository = driverRepository;
	}
	
	
	public ClearListController() {}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ClearList> getAll(){
		return clearListRepository.findAll();
	}
	
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ClearList create(@RequestBody ClearList clearList){
		return clearListRepository.save(clearList);
	}
	
	@RequestMapping(value = "/getbyid/{listId}", method = RequestMethod.GET)
	public static ClearList getBylistId(@PathVariable long listId){
		
		return clearListRepository.findBylistId(listId);
	}
	
	@RequestMapping(value = "/delete/{listId}", method = RequestMethod.GET)
	public List<ClearList> remove(@PathVariable long listId){
		clearListRepository.deleteById(listId);
		
		return clearListRepository.findAll();
	}
	
	@RequestMapping(value = "/getqueue/{listId}", method = RequestMethod.GET)
	public List<Driver> getQueue(@PathVariable long listId){
		ClearList cList = ClearListController.getBylistId(listId);
		
		return cList.getQueue();
	}
	
	//@RequestMapping(value = "/adddriver/{DriverCallsign}", method = RequestMethod.GET)
	//public String addDriverToList(@PathVariable long DriverCallsign) {
		//List<Driver> driver = driverRepository.findByDriverCallsign(DriverCallsign);
		//Driver driver = driverRepository.findById(driverCallsign);
		/*Optional<ClearList> cList = clearListRepository.findById(listId);
		ClearList clearList = cList.get();
		ArrayList<Driver> queue = clearList.getQueue();
		queue.add(driver);
		clearList.setQueue(queue);
		clearListRepository.save(clearList); */
		
		//return "Driver successfully added to clearlist";
	//}
}
