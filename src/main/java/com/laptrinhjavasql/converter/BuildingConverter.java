package com.laptrinhjavasql.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.enums.BuildingTypesEnum;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingConverter {
	
	private ModelMapper modelMapper;
	
	public BuildingConverter() {
		modelMapper = new ModelMapper();
	}
	
	public BuildingModel covertToModelFromEntity(BuildingEntity entity) {
		BuildingModel model = modelMapper.map(entity, BuildingModel.class);
		List<String> names = new ArrayList<String>();
		String[] codes = model.getTypes().split(",");
		for(String c : codes) {
			String name = BuildingTypesEnum.findNameByCode(c);
			names.add(name);
		}
 		model.setTypes(String.join(", ", names));
		return model;
	}
	
}
