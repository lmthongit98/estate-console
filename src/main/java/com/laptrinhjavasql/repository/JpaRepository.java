package com.laptrinhjavasql.repository;

import java.util.List;

public interface JpaRepository<T> {
	List<T> findAll();
}
