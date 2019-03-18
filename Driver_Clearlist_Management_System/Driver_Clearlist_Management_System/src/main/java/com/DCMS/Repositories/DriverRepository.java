package com.DCMS.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DCMS.Entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	//Optional<Driver> findByDriverCallsign (long DriverCallsign);
	Driver findBydriverId(long driverId);
	List<Driver> findByDriverCallsign(long driverCallsign);
}
