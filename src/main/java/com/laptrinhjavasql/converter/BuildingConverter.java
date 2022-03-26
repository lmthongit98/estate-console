package com.laptrinhjavasql.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingConverter {
	
	private ModelMapper modelMapper;
	
	public BuildingConverter() {
		modelMapper = new ModelMapper();
	}
	
	public BuildingModel covertToModelFromEntity(BuildingEntity entity) {
		BuildingModel model = modelMapper.map(entity, BuildingModel.class);
		
		return model;
	}
	
}
