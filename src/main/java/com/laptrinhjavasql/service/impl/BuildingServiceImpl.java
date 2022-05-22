package com.laptrinhjavasql.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.converter.BuildingConverter;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.repository.BuildingRepository;
import com.laptrinhjavasql.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavasql.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	
	private BuildingRepository buildingRepository;
	
	private BuildingConverter converter;
	
	public BuildingServiceImpl() {
		buildingRepository = new BuildingRepositoryImpl();
		converter = new BuildingConverter();
	}
	
	@Override
	public List<BuildingModel> findAll() {
		List<BuildingEntity> entities = buildingRepository.findAll();
		List<BuildingModel> models = new ArrayList<BuildingModel>();
		
		entities.forEach(entity -> {
			BuildingModel model = converter.covertToModelFromEntity(entity);
			models.add(model);
		});
		
		return models;
	}


	@Override
	public List<BuildingModel> findByCondition(BuildingSearchBuilder builder) {
		List<BuildingEntity> entities = buildingRepository.search(builder);
		List<BuildingModel> models = new ArrayList<BuildingModel>();
		
		entities.forEach(entity -> {
			BuildingModel model = converter.covertToModelFromEntity(entity);
			models.add(model);
		});
		
		return models;
	}

	@Override
	public BuildingModel findById(Long id) {
		BuildingEntity entity = buildingRepository.findById(id);
		return converter.covertToModelFromEntity(entity);
	}

	@Override
	public Long add(BuildingEntity buildingEntity) {
		return buildingRepository.insert(buildingEntity);
	}

	@Override
	public void delete(Long id) {
		buildingRepository.delete(id);
	}

	@Override
	public void update(BuildingEntity buildingEntity) {
		buildingRepository.update(buildingEntity);
	}

}
