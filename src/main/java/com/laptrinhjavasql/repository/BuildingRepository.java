package com.laptrinhjavasql.repository;

import java.util.List;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity> {
	List<BuildingEntity> search(BuildingSearchBuilder builder);
}
