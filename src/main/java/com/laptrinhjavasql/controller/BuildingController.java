package com.laptrinhjavasql.controller;

import java.util.List;

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
	

	public List<BuildingModel> searchBuilding(String name, int numberOfBasement, int floorArea, List<String> types) {
		return service.searchBuilding(name, numberOfBasement, floorArea, types);
	}
	
	public BuildingModel findById(Long id) {
		return service.findById(id);
	}
	
}
