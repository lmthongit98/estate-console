package com.laptrinhjavasql.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;
import com.laptrinhjavasql.repository.JpaRepository;
import com.laptrinhjavasql.util.ConnectionUtil;

public class SimpleRepository<T> implements JpaRepository<T> {

	@Override
	public List<T> findAll() {
		List<T> results = new ArrayList<>();
		Connection conn = null;
		String tableName = null;
		Statement stmt = null;
		ResultSet rs = null;

		@SuppressWarnings("unchecked")
		Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM " + tableName;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			Field[] fields = tClass.getDeclaredFields();

			while (rs.next()) {
				T object = tClass.newInstance();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String colName = resultSetMetaData.getColumnName(i + 1);
					Object colValue = rs.getObject(i + 1);
					for (Field field : fields) {
						if (field.isAnnotationPresent(Column.class)) {
							Column col = field.getAnnotation(Column.class);
							if (col.name().equals(colName) && colValue != null) {
								BeanUtils.setProperty(object, field.getName(), colValue);
								break;
							}
						}
					}
				}
				results.add(object);
			}

		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return results;
	}

}
