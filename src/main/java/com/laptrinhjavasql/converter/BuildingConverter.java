package com.laptrinhjavasql.converter;


import org.modelmapper.ModelMapper;

import com.laptrinhjavasql.entity.BuildingEntity;

import com.laptrinhjavasql.model.BuildingModel;

public class BuildingConverter {
	
	private final ModelMapper modelMapper;
	
	public BuildingConverter() {
		modelMapper = new ModelMapper();
	}
	
	public BuildingModel covertToModelFromEntity(BuildingEntity buildingEntity) {
		return modelMapper.map(buildingEntity, BuildingModel.class);
	}
	
}
