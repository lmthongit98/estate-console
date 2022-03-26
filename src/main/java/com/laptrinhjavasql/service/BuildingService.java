package com.laptrinhjavasql.service;

import java.util.List;

import com.laptrinhjavasql.model.BuildingModel;

public interface BuildingService {
	List<BuildingModel> searchBuilding(String name, Integer numberOfBasement, Integer floorArea, List<String> types);
}
