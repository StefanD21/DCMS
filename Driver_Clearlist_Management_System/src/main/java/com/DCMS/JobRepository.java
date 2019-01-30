package com.DCMS;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long>{
	Job findByjobId(long JobId);
	List<Job> findBydriverCallsign(long DriverCallsign);
	Job findTopByDriverCallsignOrderByDateDesc(long DriverCallsign);
}
