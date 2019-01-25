package com.DCMS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClearList {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long listId;
	
	private String listName;
	public ArrayList<Driver> queue = new ArrayList<>();
	
	
	public ClearList() {}
	
	public ClearList(long listId, String listName) {
		this.listId = listId;
		this.listName = listName;
	}
	
	public void setQueue(ArrayList<Driver> queue){
		this.queue = queue;
	}
	
	public List<Driver> getQueue(){
		return queue;
	}
	
	public long getListId() {
		return listId;
	}

	public void setListId(long listId) {
		this.listId = listId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}


}
