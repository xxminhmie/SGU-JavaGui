package xxminhmie.sgu.javagui.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import xxminhmie.sgu.javagui.dao.GenericDAO;
import xxminhmie.sgu.javagui.mapper.RowMapper;

public class PracticeAbstractDAO<T> implements GenericDAO<T> {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	//select
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = this.getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		}finally {
//			this.close();
		}
	}
	protected void setParameter(PreparedStatement statement,Object...parameters) {
		for(int i=0; i<parameters.length; i++) {
			Object parameter = parameters[i];
			int index = i+1;
			if(parameter instanceof Long) {
				
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

}
