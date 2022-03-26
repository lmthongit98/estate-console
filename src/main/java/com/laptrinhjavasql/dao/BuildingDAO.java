package com.laptrinhjavasql.dao;

import java.util.List;

import com.laptrinhjavasql.entity.BuildingEntity;

public interface BuildingDAO {
	List<BuildingEntity> searchBuilding(String name, Integer numberOfBasement, Integer floorArea, List<String> types);
}
