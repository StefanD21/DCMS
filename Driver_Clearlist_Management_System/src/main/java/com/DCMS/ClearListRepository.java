package com.DCMS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClearListRepository extends JpaRepository<ClearList, Long>{
	List<ClearList> findBylistId(long listId);
}
