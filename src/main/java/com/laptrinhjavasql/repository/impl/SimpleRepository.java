package com.laptrinhjavasql.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		List<T> results = new ArrayList<>();
		Connection conn = null;
		String tableName = null;
		Statement stmt = null;
		ResultSet rs = null;

		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}

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

		List<T> results = new ArrayList<>();
		Connection conn = null;
		String tableName = null;
		Statement stmt = null;
		ResultSet rs = null;

		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			tableName = table.name();
		}

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
