package com.laptrinhjavasql.repository;

import java.util.List;

public interface JpaRepository<T> {
	List<T> findAll();
	T findById(Long id);
	List<T> findByCondition(String sql);
	Long insert(Object object);
	// update
	void delete(Long id);
}
