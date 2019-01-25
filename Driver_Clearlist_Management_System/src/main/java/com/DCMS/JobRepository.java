package com.DCMS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long>{
	List<Job> findByDriverCallsign(long DriverCallsign);
	
}
