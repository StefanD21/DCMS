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
@RequestMapping (value = "/clearlist")
public class ClearListController {

	static ClearListRepository clearListRepository;
	
	@Autowired
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
	
	@RequestMapping(value = "/specifc/{listId}", method = RequestMethod.GET)
	public static Optional<ClearList> GetSpecifc(@PathVariable long listId){
		return clearListRepository.findById(listId);
	}
	
	@RequestMapping(value = "/delete/{listId}", method = RequestMethod.GET)
	public List<ClearList> remove(@PathVariable long listId){
		clearListRepository.deleteById(listId);
		return clearListRepository.findAll();
	}
}
