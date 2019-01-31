package com.DCMS.repositories;

import com.DCMS.entities.DriverEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverEntity, Long>{
	DriverEntity findByDriverId(long listId);
}
