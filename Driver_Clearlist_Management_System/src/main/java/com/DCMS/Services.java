package com.DCMS;

import java.util.List;
import java.util.Optional;

public class Services {
	
	public long getLastJob(long driverId, ClearList clearListId) {
		JobController.
	}

	public void sortList(long listId) {
		//sort list by timesincelastjob
	}
	
	public void addDriverClearList(ClearList clearListId, Driver driverId) {
		//add a driver to a specific clearList
		List<Driver> cqueue = clearListId.getQueue();
		
		driverId.setTimeSinceLastJob(0);
	}
	
	public void removeDriverClearList(ClearList clearListId, Driver driverId) {
		//remove driver from a specific clearList
	}
	
}
