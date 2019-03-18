package com.DCMS.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DCMS.Entities.ClearList;


public interface ClearListRepository extends JpaRepository<ClearList, Long>{
	ClearList findBylistId(long listId);
}
