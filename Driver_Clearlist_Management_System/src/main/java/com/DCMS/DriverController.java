package com.DCMS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/driver")
public class DriverController {
	 
	static DriverRepository driverRepository;
	 
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
		
		@RequestMapping(value = "/getbyid/{driverId}", method = RequestMethod.GET)
		public static Driver getBydriverId(@PathVariable long driverId){
			
			return driverRepository.findBydriverId(driverId);
		}
		
		@RequestMapping(value = "/delete/{driverId}", method = RequestMethod.GET)
		public List<Driver> remove(@PathVariable long driverId){
			driverRepository.deleteById(driverId);
			return driverRepository.findAll();
		}
		
		
}
