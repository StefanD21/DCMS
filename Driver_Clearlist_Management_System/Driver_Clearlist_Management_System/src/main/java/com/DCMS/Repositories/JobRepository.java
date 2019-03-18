package com.DCMS.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.DCMS.Entities.ClearList;
import com.DCMS.Entities.Driver;
import com.DCMS.Entities.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	Job findByjobId(long JobId);
	List<Job> findByDriver(Driver Driver);
	Job findTopByDriverOrderByDateDesc(Driver Driver);
	Job findTopByclearlistAndDriverOrderByDateDesc(ClearList clearlist, Driver Driver);
	Job findTopByclearlist(ClearList clearlist);
}
