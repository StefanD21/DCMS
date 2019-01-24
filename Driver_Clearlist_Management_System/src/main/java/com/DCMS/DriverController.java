package com.DCMS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/driver")
public class DriverController {
	 
	DriverRepository driverRepository;
	 
	 @Autowired
	 public DriverController(DriverRepository driverRepository) {
		 this.driverRepository = driverRepository;
	 }
	 
	 public DriverController() {}
	 
	 @RequestMapping(value = "/all", method = RequestMethod.GET)
		public List<Driver> getAll(){
			return driverRepository.findAll();
		}

		@RequestMapping(value = "/create", method = RequestMethod.POST)
		public Driver create(@RequestBody Driver driver){
			
			return driverRepository.save(driver);
		}
}
