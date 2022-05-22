package com.laptrinhjavasql.repository.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.laptrinhjavasql.builder.BuildingSearchBuilder;
import com.laptrinhjavasql.constant.SystemConstant;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.repository.BuildingRepository;
import com.laptrinhjavasql.util.ValidateUtil;

public class BuildingRepositoryImpl extends SimpleRepository<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> search(BuildingSearchBuilder builder) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.id, b.name, b.district_id,")
				.append(" b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphone")
				.append(" FROM building b");
		buildJoinQuery(builder, sql);
		sql.append(" " + SystemConstant.WHERE_ONE_EQUALS_ONE);
		buildWhereClausePart1(builder, sql);
		buildWhereClausePart2(builder, sql);
		sql.append(" group by b.id");

		return super.findByCondition(sql.toString());
	}

	private void buildJoinQuery(BuildingSearchBuilder builder, StringBuilder query) {

		query.append(" INNER JOIN district ON b.district_id = district.id");

		if(builder.getStaffId() != null) {
			query.append(" INNER JOIN assignment_building ON b.id = assignment_building.building_id");
			query.append(" INNER JOIN user ON assignment_building.staff_id = user.id");
		}

		if(builder.getBuildingTypes() != null && builder.getBuildingTypes().size() > 0) {
			query.append(" INNER JOIN building_renttype ON b.id = building_renttype.building_id")
					.append(" INNER JOIN renttype ON building_renttype.renttype_id = renttype.id");
		}

		if(builder.getAreaRentFrom() != null || builder.getAreaRentTo() != null) {
			query.append(" JOIN rentarea ON b.id = rentarea.building_id");
		}

	}

	private void buildWhereClausePart1(BuildingSearchBuilder builder, StringBuilder query) {
		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				if (fieldName.equals("district") || fieldName.equals("staffId") || fieldName.equals("buildingTypes")
						|| fieldName.toLowerCase().startsWith("costrent") || fieldName.toLowerCase().startsWith("arearent")) {
					continue;
				}

				Object objValue = field.get(builder);
				if (objValue == null) {
					continue;
				}

				if (field.getType().getName().equals("java.lang.String")) {
					query.append(" AND b." + fieldName.toLowerCase() + " like '%" + objValue + "%'");
				} else if (field.getType().getName().equals("java.lang.Integer")) {
					query.append(" AND b." + fieldName.toLowerCase() + " = " + objValue);
				}

			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	private void buildWhereClausePart2(BuildingSearchBuilder builder, StringBuilder query) {
		if (builder.getCostRentFrom() != null) {
			query.append(" AND b.rentprice >= " + builder.getCostRentFrom());
		}
		if (builder.getCostRentTo() != null) {
			query.append(" AND b.rentprice <= " + builder.getCostRentTo());
		}

		if (builder.getAreaRentFrom() != null || builder.getAreaRentTo() != null) {
			query.append(" AND EXISTS (SELECT * FROM rentarea ra where b.id = ra.building_id");
			if (builder.getAreaRentFrom() != null) {
				query.append(" AND rentarea.value >=" + builder.getAreaRentFrom());
			}
			if (builder.getAreaRentTo() != null) {
				query.append(" AND rentarea.value <= " + builder.getAreaRentTo());
			}
			query.append(")");
		}

		if(builder.getBuildingTypes() != null && builder.getBuildingTypes().size() > 0) {
			String sqlType = builder.getBuildingTypes().stream().map(type -> "renttype.code = '" + type + "'").collect(Collectors.joining(" OR "));
			query.append(" AND (").append(sqlType).append(")");
		}

		if(builder.getStaffId() != null) {
			query.append(" AND user.id = " + builder.getStaffId());
		}

		if(builder.getDistrict() != null) {
			query.append(" AND district.code like '%" + builder.getDistrict() + "%'");
		}

	}

}
