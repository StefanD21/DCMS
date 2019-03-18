package com.DCMS.Controllers;

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

import com.DCMS.Services;
import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Repositories.ClearListRepository;
import com.DCMS.Repositories.DriverRepository;


@RestController
@RequestMapping (value = "/clearlists")
public class ClearListController {

	static ClearListRepository clearListRepository;
	DriverRepository driverRepository;
	
	
	@Autowired
	public ClearListController(ClearListRepository clearListRepository,
			DriverRepository driverRepository) {
		this.clearListRepository = clearListRepository;
		this.driverRepository = driverRepository;
	}
	
	
	public ClearListController() {}
	//creates a new clearlist
	@RequestMapping(value = "/{listName}/add", method = RequestMethod.POST)
	public String addList(
			@PathVariable String listName
			){
		ClearList clearList = new ClearList();
		clearList.setListName(listName);
		clearList.setActive(false);
		clearListRepository.save(clearList);
		
		return "A Clearlist has been successfully created";
	}
	
	//deletes a clearlist by its given ID
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteList(){
		ClearList list = clearListRepository.findById(getActiveListId()).get();
		String listN = list.getListName();
		clearListRepository.deleteById(getActiveListId());
		
		return String.format("The Clearlist %s has been deleted",listN);
	}
	
	//returns a list of clearlists
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public static List<ClearList> getAllClearlists(){
		
		return clearListRepository.findAll();
	}
	
	//returns a list given by its ID 
	@RequestMapping(value = "/{listId}", method = RequestMethod.GET)
	public static ClearList getClearlistById(@PathVariable long listId){
		
		return clearListRepository.findBylistId(listId);
	}
	
	//returns a clearlists queue
	@RequestMapping(value = "/{listId}/queue", method = RequestMethod.GET)
	public List<Driver> getQueue(@PathVariable long listId){
		ClearList cList = ClearListController.getClearlistById(listId);
		
		return cList.getQueue();
	}
	
	//gets the current active list
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public static ClearList getActiveList() {
		ClearList clist = new ClearList();
		ArrayList<ClearList> lists = new ArrayList<>();
		lists.addAll(ClearListController.getAllClearlists());
		for (ClearList list:lists) {
			if(list.isActive() == true) {
				clist = list;
			}
		}
		return clist;
	}
	
	@RequestMapping(value = "/activeid", method = RequestMethod.GET)
	public static long getActiveListId() {
		ClearList clist = new ClearList();
		ArrayList<ClearList> lists = new ArrayList<>();
		lists.addAll(ClearListController.getAllClearlists());
		for (ClearList list:lists) {
			if(list.isActive() == true) {
				clist = list;
			}
		}
		return clist.getListId();
	}
	
	//changes the active clearList
	@RequestMapping(value = "/{listId}/active", method = RequestMethod.GET)
	public static String changeActiveList(@PathVariable long listId) {
		ClearList aList = ClearListController.getClearlistById(listId);
		ArrayList<ClearList> lists = new ArrayList<>();
		lists.addAll(ClearListController.getAllClearlists());
		for (ClearList list:lists) {
			list.setActive(false);
		}
		aList.setActive(true);
		clearListRepository.save(aList);
		String listname = aList.getListName();
		return String.format("The active list has successfully been changed to %s",listname);
	}
}
