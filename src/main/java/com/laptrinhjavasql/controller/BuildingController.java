package com.laptrinhjavasql.controller;

import java.util.List;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.service.BuildingService;
import com.laptrinhjavasql.service.impl.BuildingServiceImpl;

public class BuildingController {
	
	private BuildingService service;
	
	public BuildingController() {
		service = new BuildingServiceImpl();
	}
	
	public List<BuildingModel> findAll() {
		return service.findAll();
	}

	public List<BuildingModel> findByCondition(String name, int numberOfBasement, int floorArea, List<String> types) {
		return service.findByCondition(name, numberOfBasement, floorArea, types);
	}
	
	public BuildingModel findById(Long id) {
		return service.findById(id);
	}

	public Long add(BuildingEntity buildingEntity) {
		return service.add(buildingEntity);
	}

	public void delete(Long id) {
		service.delete(id);
	}

	public void update(BuildingEntity buildingEntity) {
		service.update(buildingEntity);
	}
}
