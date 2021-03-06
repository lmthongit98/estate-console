package com.laptrinhjavasql.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavasql.anotation.Column;
import com.laptrinhjavasql.anotation.Entity;
import com.laptrinhjavasql.anotation.Table;
import com.laptrinhjavasql.mapper.ResultSetMapper;
import com.laptrinhjavasql.repository.JpaRepository;
import com.laptrinhjavasql.util.ConnectionUtil;

public class SimpleRepository<T> implements JpaRepository<T> {

	private Class<T> tClass;
	private ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();

	@SuppressWarnings("unchecked")
	public SimpleRepository() {
		tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll() {
		String tableName = getTableName();
		if (tableName == null) {
			return null;
		}

		List<T> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM " + tableName;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			results = resultSetMapper.mapRow(rs, tClass);

			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, rs);
		}

		return null;
	}

	@Override
	public T findById(Long id) {
		String tableName = getTableName();
		if (tableName == null) {
			return null;
		}

		List<T> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM " + tableName + "  WHERE id = " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			results = resultSetMapper.mapRow(rs, tClass);

			return results.size() > 0 ? results.get(0) : null;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, rs);
		}

		return null;
	}

	@Override
	public List<T> findByCondition(String sql) {
		List<T> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			results = resultSetMapper.mapRow(rs, tClass);

			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, rs);
		}

		return null;
	}

	@Override
	public void delete(Long id) {
		String tableName = getTableName();
		if (tableName == null) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "DELETE FROM " + tableName + "  WHERE id = " + id;
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			closeConnection(conn, stmt, null);
		}
	}

	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			StringBuilder sql = createSqlUpdate();
			pstmt = conn.prepareStatement(sql.toString());

			Class<?> zClass = object.getClass();
			Field[] fields = zClass.getDeclaredFields();
			int paramIndex = 1;
			for (Field field : fields) {
				if(!field.isAnnotationPresent(Column.class))
					continue;
				field.setAccessible(true);
				pstmt.setObject(paramIndex++, field.get(object));
			}

			//Scan parent's fields
			Class<?> parentClass = tClass.getSuperclass();
			Field[] parentFields = parentClass.getDeclaredFields();
			Long id = null;
			while(parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
				for (Field field : parentFields) {
					field.setAccessible(true);
					if(!field.isAnnotationPresent(Column.class))
						continue;
					if(!field.getName().equals("id")) {
						pstmt.setObject(paramIndex++, field.get(object));
					} else {
						id = (Long) field.get(object);
					}
				}
				parentClass = parentClass.getSuperclass();
			}
			//set ID
			if(id != null) {
				pstmt.setObject(paramIndex, id);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, pstmt, null);
		}
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnection();
			StringBuilder sql = createSqlInsert();
			if(sql == null) {
				return null;
			}
			pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			Class<?> zClass = object.getClass();
			Field[] fields = zClass.getDeclaredFields();
			int paramIndex = 1;
			for (Field field : fields) {
				field.setAccessible(true);
				pstmt.setObject(paramIndex++, field.get(object));
			}

			//Scan parent's fields
			Class<?> parentClass = tClass.getSuperclass();
			Field[] parentFields = parentClass.getDeclaredFields();
			int paramParentIndex = fields.length + 1;
			while(parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
				for (Field field : parentFields) {
					if(!field.isAnnotationPresent(Column.class) || field.getName().equals("id"))
						continue;
					field.setAccessible(true);
					pstmt.setObject(paramParentIndex++, field.get(object));
				}
				parentClass = parentClass.getSuperclass();
			}

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				rs = pstmt.getGeneratedKeys();
				return rs.next() ? rs.getLong(1) : null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return null;
	}

	private StringBuilder createSqlInsert() {
		String tableName = getTableName();
		if (tableName == null) {
			return null;
		}

		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");

		for (Field field : tClass.getDeclaredFields()) {
			if (!field.isAnnotationPresent(Column.class))
				continue;
			if (fields.length() > 1) {
				fields.append(", ");
				params.append(", ");
			}
			Column column = field.getAnnotation(Column.class);
			fields.append(column.name());
			params.append("?");
		}

		//Scan parent's fields
		Class<?> parentClass = tClass.getSuperclass();
		Field[] parentFields = parentClass.getDeclaredFields();
		while(parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
			for (Field field : parentFields) {
				if(!field.isAnnotationPresent(Column.class) || field.getName().equals("id"))
					continue;
				fields.append(", ");
				params.append(", ");
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
			parentClass = parentClass.getSuperclass();
		}

		StringBuilder result = new StringBuilder(
				"INSERT INTO " + tableName + "(" + fields.toString() + ") VALUES(" + params.toString() + ")");

		return result;
	}

	private StringBuilder createSqlUpdate() {
		String tableName = getTableName();
		if (tableName == null) {
			return null;
		}

		StringBuilder fieldAndParams = new StringBuilder("");
		for (Field field : tClass.getDeclaredFields()) {
			if (!field.isAnnotationPresent(Column.class))
				continue;
			if (fieldAndParams.length() > 1) {
				fieldAndParams.append(", ");
			}
			Column column = field.getAnnotation(Column.class);
			fieldAndParams.append(column.name() + " = ?");
		}

		//Scan parent's fields
		Class<?> parentClass = tClass.getSuperclass();
		Field[] parentFields = parentClass.getDeclaredFields();
		while(parentClass != null && parentClass.isAnnotationPresent(Entity.class)) {
			for (Field field : parentFields) {
				if (!field.isAnnotationPresent(Column.class) || field.getName().equals("id"))
					continue;
				Column column = field.getAnnotation(Column.class);
				fieldAndParams.append(", " + column.name() + " = ?");
			}
			parentClass = parentClass.getSuperclass();
		}
		StringBuilder result = new StringBuilder(
				"UPDATE " + tableName + " SET " + fieldAndParams + " WHERE id = ?");

		return result;
	}

	private String getTableName() {
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			return table.name();
		}
		return null;
	}

	private void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
