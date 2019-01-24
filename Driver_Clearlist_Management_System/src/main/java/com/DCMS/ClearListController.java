package com.DCMS;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/clearlist")
public class ClearListController {

	ClearListRepository clearListRepository;
	
	public ClearListController(ClearListRepository clearListRepository) {
		this.clearListRepository = clearListRepository;
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
}
