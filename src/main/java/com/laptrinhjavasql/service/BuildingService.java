package com.laptrinhjavasql.service;

import java.util.List;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;

public interface BuildingService {
	List<BuildingModel> findAll();
	List<BuildingModel> searchBuilding(String name, Integer numberOfBasement, Integer floorArea, List<String> types);
	BuildingModel findById(Long id);
	Long add(BuildingEntity buildingEntity);
}
