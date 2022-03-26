package com.laptrinhjavasql.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.converter.BuildingConverter;
import com.laptrinhjavasql.dao.BuildingDAO;
import com.laptrinhjavasql.dao.impl.BuildingDAOImpl;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	
	private BuildingDAO dao;
	
	private BuildingConverter converter;
	
	public BuildingServiceImpl() {
		dao = new BuildingDAOImpl();
		converter = new BuildingConverter();
	}

	@Override
	public List<BuildingModel> searchBuilding(String name, Integer numberOfBasemnet, Integer floorArea, List<String> types) {
		List<BuildingEntity> entities = dao.searchBuilding(name, numberOfBasemnet, floorArea, types);
		List<BuildingModel> models = new ArrayList<BuildingModel>();
		
		entities.forEach(entity -> {
			BuildingModel model = converter.covertToModelFromEntity(entity);
			models.add(model);
		});
		
		return models;
	}

}
