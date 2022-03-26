package com.laptrinhjavasql.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.constant.SystemContant;
import com.laptrinhjavasql.dao.BuildingDAO;
import com.laptrinhjavasql.entity.BuildingEntity;
import com.laptrinhjavasql.util.ConnectionUtil;
import com.laptrinhjavasql.util.ValidateUtil;

public class BuildingDAOImpl implements BuildingDAO {

	@Override
	public List<BuildingEntity> searchBuilding(String name, Integer numberOfBasement, Integer floorArea,
			List<String> types) {
		StringBuilder finalQuery = new StringBuilder(
				"SELECT distinct b.name, b.floorarea, b.numberofbasement from building b");
		StringBuilder joinQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder(SystemContant.WHERE_TRUE);

		buildQueryWithJoin(types, joinQuery, whereQuery);

		buildQueryWithoutJoin(name, numberOfBasement, floorArea, whereQuery);

		finalQuery.append(joinQuery).append(whereQuery);

		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		try (Connection conn = ConnectionUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(finalQuery.toString());) {
			while (rs.next()) {
				BuildingEntity entity = new BuildingEntity();
				entity.setName(rs.getString("name"));
				entity.setFloorArea(rs.getInt("floorarea"));
				entity.setNumberOfBasement(rs.getInt("numberofbasement"));
				results.add(entity);
			}

			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void buildQueryWithoutJoin(String name, Integer numberOfBasement, Integer floorArea,
			StringBuilder whereQuery) {
		if (ValidateUtil.isNotBlank(name)) {
			whereQuery.append(" AND b.name like '%").append(name).append("%'");
		}

		if (ValidateUtil.isNotBlank(floorArea)) {
			whereQuery.append(" AND b.floorarea = ").append(floorArea);
		}

		if (ValidateUtil.isNotBlank(numberOfBasement)) {
			whereQuery.append(" AND b.numberofbasement = ").append(numberOfBasement);
		}
	}

	private void buildQueryWithJoin(List<String> types, StringBuilder joinQuery, StringBuilder whereQuery) {
		if (ValidateUtil.isNotBlank(types)) {
			joinQuery.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid")
					.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id");

			List<String> conditions = new ArrayList<String>();
			types.forEach(type -> conditions.add("renttype.code = '" + type + "'"));

			whereQuery.append(" AND (").append(String.join(" OR ", conditions)).append(")");
		}
	}

}
