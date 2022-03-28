package com.laptrinhjavasql.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.util.CommonUtil;

public class BuildingConverter {
	
	private ModelMapper modelMapper;
	
	public BuildingConverter() {
		modelMapper = new ModelMapper();
	}
	
	public BuildingModel covertToModelFromEntity(BuildingEntity entity) {
		BuildingModel model = modelMapper.map(entity, BuildingModel.class);
		model.setTypes(CommonUtil.covertTypeCodeToName(model.getTypes()));
		return model;
	}
	
}
