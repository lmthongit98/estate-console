package com.laptrinhjavasql.repository;

import java.util.List;

import com.laptrinhjavasql.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity> {
//	List<BuildingEntity> searchBuilding(String name, Integer numberOfBasement, Integer floorArea, List<String> types);
}
