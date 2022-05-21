package com.laptrinhjavasql.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.constant.SystemConstant;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.repository.BuildingRepository;
import com.laptrinhjavasql.util.ValidateUtil;

public class BuildingRepositoryImpl extends SimpleRepository<BuildingEntity> implements BuildingRepository {
	
	@Override
	public List<BuildingEntity> searchBuilding(String name, Integer numberOfBasement, Integer floorArea,
			List<String> types) {
		StringBuilder finalQuery = new StringBuilder(
				"SELECT distinct b.name, b.floorarea, b.numberofbasement, b.types from building b");
		StringBuilder joinQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder(SystemConstant.WHERE_ONE_EQUALS_ONE);

		buildQueryWithoutJoin(name, numberOfBasement, floorArea, types, whereQuery);
		finalQuery.append(joinQuery).append(whereQuery);
		
		return super.findByCondition(finalQuery.toString());
	}

	private void buildQueryWithoutJoin(String name, Integer numberOfBasement, Integer floorArea,
			List<String> types, StringBuilder whereQuery) {
		if (ValidateUtil.isNotBlank(name)) {
			whereQuery.append(" AND b.name like '%").append(name).append("%'");
		}

		if (ValidateUtil.isNotBlank(floorArea)) {
			whereQuery.append(" AND b.floorarea = ").append(floorArea);
		}

		if (ValidateUtil.isNotBlank(numberOfBasement)) {
			whereQuery.append(" AND b.numberofbasement = ").append(numberOfBasement);
		}
		
		if(ValidateUtil.isNotBlank(types)) {
			List<String> conditions = new ArrayList<String>();
			types.forEach(type -> conditions.add("b.types like '%" + type + "%'"));
			whereQuery.append(" AND (").append(String.join(" OR ", conditions)).append(")");
		}
	}

}
