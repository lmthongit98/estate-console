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
import com.laptrinhjavasql.anotation.Entity;

public class ResultSetMapper<T> {
	
	public List<T> mapRow(ResultSet rs, Class<T> tClass) {
		try {
			List<T> results = new ArrayList<>();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			Field[] fields = null;

			while (rs.next()) {
				T object = tClass.newInstance();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String colName = resultSetMetaData.getColumnName(i + 1);
					Object colValue = rs.getObject(i + 1);
					
					// scan field of current class
					fields = tClass.getDeclaredFields();
					mapValueToObjectField(fields, object, colName, colValue);
					
					// scan field of parent class
					Class<?> supperClass = tClass.getSuperclass();
					while(supperClass != null && supperClass.isAnnotationPresent(Entity.class)) {
						fields = supperClass.getDeclaredFields();
						mapValueToObjectField(fields, object, colName, colValue);
						supperClass = supperClass.getSuperclass();
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

	private void mapValueToObjectField(Field[] fields, T object, String colName, Object colValue)
			throws IllegalAccessException, InvocationTargetException {
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

}
