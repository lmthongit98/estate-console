package com.laptrinhjavasql.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.converter.BuildingConverter;
import com.laptrinhjavasql.entity.AssignmentBuildingEntity;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.entity.DistrictEntity;
import com.laptrinhjavasql.entity.RentAreaEntity;
import com.laptrinhjavasql.model.BuildingModel;
import com.laptrinhjavasql.model.BuildingSearchInput;
import com.laptrinhjavasql.repository.*;
import com.laptrinhjavasql.repository.impl.AssignmentBuildingRepositoryImpl;
import com.laptrinhjavasql.repository.impl.BuildingRepositoryImpl;
import com.laptrinhjavasql.repository.impl.RentAreaRepositoryImpl;
import com.laptrinhjavasql.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	
	private final BuildingRepository buildingRepository;
	private final AssignmentBuildingRepository assignmentBuildingRepository;
	private final RentAreaRepository rentAreaRepository;
	private final DistrictRepository districtRepository;
	private final BuildingConverter converter;

	public BuildingServiceImpl() {
		buildingRepository = new BuildingRepositoryImpl();
		districtRepository = new DistrictRepositoryImpl();
		assignmentBuildingRepository = new AssignmentBuildingRepositoryImpl();
		rentAreaRepository = new RentAreaRepositoryImpl();
		converter = new BuildingConverter();
	}
	
	@Override
	public List<BuildingModel> findAll() {
		List<BuildingEntity> entities = buildingRepository.findAll();
		return convertToModelsFromEntities(entities);
	}


	@Override
	public List<BuildingModel> findByCondition(BuildingSearchInput buildingSearchInput) {
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchInput.getName())
                .setStreet(buildingSearchInput.getStreet())
                .setStaffId(buildingSearchInput.getStaffId())
                .setNumberOfBasement(buildingSearchInput.getNumberOfBasement())
                .setBuildingArea(buildingSearchInput.getFloorArea())
                .setBuildingTypes(buildingSearchInput.getBuildingTypes())
                .setManagerName(buildingSearchInput.getManagerName())
				.setAreaRentTo(buildingSearchInput.getAreaRentTo())
				.setAreaRentFrom(buildingSearchInput.getAreaRentFrom())
				.setCostRentFrom(buildingSearchInput.getCostRentFrom())
				.setCostRentTo(buildingSearchInput.getCostRentTo())
                .build();

		List<BuildingEntity> entities = buildingRepository.search(builder);
		return convertToModelsFromEntities(entities);
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
		staffIdsToAdd.forEach(id -> assignmentBuildingRepository.insert(new AssignmentBuildingEntity(id, buildingId)));
	}

	private List<Long> findItemsOfSourceButNotInTarget(List<Long> source, List<Long> target) {
		List<Long> result = new LinkedList<>();
		source.forEach(srcId -> {
			Long id = target.stream().filter(targetId -> Objects.equals(targetId, srcId)).findAny().orElse(null);
			if(id == null) {
				result.add(srcId);
			}
		});
		return  result;
	}

	private List<BuildingModel> convertToModelsFromEntities(List<BuildingEntity> buildingEntities) {
		if(buildingEntities == null) {
			return null;
		}
		List<BuildingModel> buildingModels = new ArrayList<>();
		buildingEntities.forEach(buildingEntity -> {
			BuildingModel buildingModel = converter.covertToModelFromEntity(buildingEntity);

			List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findRentAreaByBuildingId(buildingEntity.getId());
			if(rentAreaEntities != null && !rentAreaEntities.isEmpty()) {
				String rentAreaStr = rentAreaEntities.stream()
						.map(area -> area.getValue() + "")
						.collect(Collectors.joining(", "));
				buildingModel.setRentArea(rentAreaStr);
			}

			DistrictEntity districtEntity = districtRepository.findById(buildingEntity.getDistrictId());
			buildingModel.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard());
			if(districtEntity != null && !"".equals(districtEntity.getName())) {
				buildingModel.setAddress(buildingModel.getAddress() + ", " + districtEntity.getName());
			}

			buildingModels.add(buildingModel);
		});
		return buildingModels;
	}


}
