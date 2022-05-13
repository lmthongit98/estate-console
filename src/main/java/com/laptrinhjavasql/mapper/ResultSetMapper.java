package com.laptrinhjavasql.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavasql.anotation.Column;

public class ResultSetMapper<T> {
	
	public List<T> mapRow(ResultSet rs, Class<T> tClass) {
		try {
			List<T> results = new ArrayList<>();
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
			return results;

		} catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		return null;
	}

}
