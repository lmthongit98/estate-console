package com.laptrinhjavasql.controller;

import java.util.List;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.model.BuildingSearchInput;
import com.laptrinhjavasql.service.BuildingService;
import com.laptrinhjavasql.service.impl.BuildingServiceImpl;

public class BuildingController {
	
	private final BuildingService service;
	
	public BuildingController() {
		service = new BuildingServiceImpl();
	}
	
	public List<BuildingModel> findAll() {
		return service.findAll();
	}

	public List<BuildingModel> findByCondition(BuildingSearchInput buildingSearchInput) {
		return service.findByCondition(buildingSearchInput);
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

    public void assignBuildingToStaffs(List<Long> updatedAssigneeIds, List<Long> oldAssigneeIds, Long buildingID) {
		service.assignBuildingToStaffs(updatedAssigneeIds, oldAssigneeIds, buildingID);
    }
}
