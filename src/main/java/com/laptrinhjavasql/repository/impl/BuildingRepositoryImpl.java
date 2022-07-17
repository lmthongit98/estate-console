package com.laptrinhjavasql.repository.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.constant.SystemConstant;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.enums.SpecialSearchParams;
import com.laptrinhjavasql.repository.BuildingRepository;

public class BuildingRepositoryImpl extends SimpleRepository<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> search(BuildingSearchBuilder builder) {
		StringBuilder finalQuery = new StringBuilder();
		StringBuilder joinQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder();

		finalQuery.append("SELECT b.id, b.name, b.district_id,")
				.append(" b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphone")
				.append(" FROM building b")
				.append(" INNER JOIN district ON b.district_id = district.id");

		buildQueryWithJoin(builder, whereQuery, joinQuery);
		buildQueryWithoutJoin(builder, whereQuery);

		finalQuery.append(joinQuery)
				.append(" " + SystemConstant.WHERE_ONE_EQUALS_ONE)
				.append(whereQuery)
				.append(" group by b.id");

		return super.findByCondition(finalQuery.toString());
	}

	private List<String> getSpecialSearchParams() {
		List<String> params = new ArrayList<>();

		for (SpecialSearchParams item : SpecialSearchParams.values()) {
			params.add(item.getValue());
		}

		return params;
	}

	private void buildQueryWithoutJoin(BuildingSearchBuilder builder, StringBuilder query) {
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);

				String fieldName = field.getName();
				Object objValue = field.get(builder);
				String selectedColumn = SystemConstant.BUILDING_ALIAS + fieldName.toLowerCase();


				if(getSpecialSearchParams().contains(fieldName)) {
					continue;
				}


				if (objValue == null) {
					continue;
				}

				if (field.getType().equals(String.class)) {
					String convertedValue = "'%" + objValue + "%'";

					query.append(SystemConstant.AND_STATEMENT)
							.append(selectedColumn)
							.append(SystemConstant.LIKE_OPERATOR)
							.append(convertedValue);
				} else if (field.getType().equals(Integer.class)) {
					query.append(SystemConstant.AND_STATEMENT)
							.append(selectedColumn)
							.append(SystemConstant.EQUAL_OPERATOR)
							.append(objValue);
				}
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private void buildQueryWithJoin(BuildingSearchBuilder builder, StringBuilder whereQuery, StringBuilder joinQuery) {
		if(builder.getAreaRentFrom() != null || builder.getAreaRentTo() != null) {
			if (builder.getCostRentFrom() != null) {
				whereQuery.append(" AND b.rentprice >= ").append(builder.getCostRentFrom());
			}
			if (builder.getCostRentTo() != null) {
				whereQuery.append(" AND b.rentprice <= ").append(builder.getCostRentTo());
			}
		}

		if (builder.getAreaRentFrom() != null || builder.getAreaRentTo() != null) {
			whereQuery.append(" AND EXISTS (SELECT * FROM rentarea ra where b.id = ra.building_id");
			if (builder.getAreaRentFrom() != null) {
				whereQuery.append(" AND ra.value >=").append(builder.getAreaRentFrom());
			}
			if (builder.getAreaRentTo() != null) {
				whereQuery.append(" AND ra.value <= ").append(builder.getAreaRentTo());
			}
			whereQuery.append(")");
		}

		if(builder.getBuildingTypes() != null && !builder.getBuildingTypes().isEmpty()) {
			joinQuery.append(" INNER JOIN building_renttype ON b.id = building_renttype.building_id");
			joinQuery.append(" INNER JOIN renttype ON building_renttype.renttype_id = renttype.id");

			String sqlType = builder.getBuildingTypes().stream().map(type -> "renttype.code = '" + type + "'").collect(Collectors.joining(" OR "));
			whereQuery.append(" AND (").append(sqlType).append(")");
		}

		if(builder.getStaffId() != null) {
			joinQuery.append(" INNER JOIN assignment_building ON b.id = assignment_building.building_id");
			joinQuery.append(" INNER JOIN user ON assignment_building.staff_id = user.id");

			whereQuery.append(" AND user.id = ").append(builder.getStaffId());
		}

		if(builder.getDistrict() != null) {
			whereQuery.append(" AND district.code like '%").append(builder.getDistrict()).append("%'");
		}

	}

}
