package com.DCMS;

import java.util.LinkedList;
import java.util.Queue;

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
	private Queue<Driver> queue = new LinkedList<>();
	
	public ClearList() {}
	
	public ClearList(long listId, String listName) {
		this.listId = listId;
		this.listName = listName;
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
