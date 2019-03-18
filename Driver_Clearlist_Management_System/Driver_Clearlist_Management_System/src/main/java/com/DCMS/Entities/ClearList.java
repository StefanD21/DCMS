package com.DCMS.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "ClearList")
@Table(name = "clearlists")
public class ClearList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clearlist_id")
	private long listId; // make relation to job clearlistId

	private String listName;
	@ManyToMany
	@JoinTable(name = "drivers_clearlists", joinColumns= {@JoinColumn(name="clearlist_id")},
		inverseJoinColumns= {@JoinColumn(name="driver_id")})
	private List<Driver> queue = new ArrayList<>();
	private boolean isActive;
	
	@OneToMany(mappedBy = "clearlist")
	@Column(name = "clearlist_id")
	private List<Job> jobs;
	
	public ClearList() {}
	
	public ClearList( String listName, List<Driver> queue,boolean isActive) {
		this.listName = listName;
		this.queue = queue;
		this.isActive = isActive;
	}
	
	
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setQueue(List<Driver> queue){
		this.queue = queue;
	}
	// might need to remove
	@JsonIgnore
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
