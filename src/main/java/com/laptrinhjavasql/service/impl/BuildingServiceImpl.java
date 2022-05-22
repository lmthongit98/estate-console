package com.laptrinhjavasql.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.converter.BuildingConverter;
import com.laptrinhjavasql.entity.AssignmentBuildingEntity;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.repository.AssignmentBuildingRepository;
import com.laptrinhjavasql.repository.BuildingRepository;
import com.laptrinhjavasql.repository.impl.AssignmentBuildingRepositoryImpl;
import com.laptrinhjavasql.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavasql.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	
	private BuildingRepository buildingRepository;
	private AssignmentBuildingRepository assignmentBuildingRepository;
	private BuildingConverter converter;
	
	public BuildingServiceImpl() {
		buildingRepository = new BuildingRepositoryImpl();
		assignmentBuildingRepository = new AssignmentBuildingRepositoryImpl();
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

	@Override
	public void assignBuildingToStaffs(List<Long> updatedAssigneeIds, List<Long> oldAssigneeIds, Long buildingId) {

		List<Long> staffIdsToDelete =  findItemsOfSourceButNotInTarget(oldAssigneeIds, updatedAssigneeIds);
		List<Long> staffIdsToAdd = findItemsOfSourceButNotInTarget(updatedAssigneeIds, oldAssigneeIds);

		staffIdsToDelete.forEach(id -> {
			Long assignedId = assignmentBuildingRepository.findAssignedId(id, buildingId);
			assignmentBuildingRepository.delete(assignedId);
		});

		staffIdsToAdd.forEach(id -> {
			assignmentBuildingRepository.insert(new AssignmentBuildingEntity(id, buildingId));
		});
	}

	private List<Long> findItemsOfSourceButNotInTarget(List<Long> source, List<Long> target) {
		List<Long> result = new LinkedList<>();
		source.forEach(srcId -> {
			Long id = target.stream().filter(targetId -> targetId == srcId).findAny().orElse(null);
			if(id == null) {
				result.add(srcId);
			}
		});
		return  result;
	}


}
