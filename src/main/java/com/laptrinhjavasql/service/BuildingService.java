package com.laptrinhjavasql.service;

import java.util.List;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;

public interface BuildingService {
	List<BuildingModel> findAll();
	List<BuildingModel> findByCondition(BuildingSearchBuilder builder);
	BuildingModel findById(Long id);
	Long add(BuildingEntity buildingEntity);
    void delete(Long id);
	void update(BuildingEntity buildingEntity);
    void assignBuildingToStaffs(List<Long> updatedAssigneeIds, List<Long> oldAssigneeIds, Long buildingId);
}
