package com.laptrinhjavasql.converter;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.entity.DistrictEntity;
import com.laptrinhjavasql.repository.DistrictRepository;
import com.laptrinhjavasql.repository.DistrictRepositoryImpl;
import org.modelmapper.ModelMapper;

import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.enums.BuildingTypesEnum;
import com.laptrinhjavasql.model.BuildingModel;

public class BuildingConverter {
	
	private ModelMapper modelMapper;

	private DistrictRepository districtRepository = new DistrictRepositoryImpl();
	
	public BuildingConverter() {
		modelMapper = new ModelMapper();
	}
	
	public BuildingModel covertToModelFromEntity(BuildingEntity buildingEntity) {
		BuildingModel buildingModel = modelMapper.map(buildingEntity, BuildingModel.class);
		DistrictEntity districtEntity = districtRepository.findById(buildingEntity.getDistrictId());
		buildingModel.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard());
		if(districtEntity != null && !"".equals(districtEntity.getName())) {
			buildingModel.setAddress(buildingModel.getAddress() + ", " + districtEntity.getName());
		}
		return buildingModel;
	}
	
}
