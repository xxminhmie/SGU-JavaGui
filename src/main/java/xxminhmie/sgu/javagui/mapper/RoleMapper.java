package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.RoleModel;

public class RoleMapper implements RowMapper<RoleModel>{

	@Override
	public RoleModel mapRow(ResultSet rs) {
		try {
			RoleModel model = new RoleModel();
			model.setId(rs.getLong("id"));
			model.setName(rs.getNString("name"));
			model.setDescription(rs.getNString("description"));
			return model;
		}catch(SQLException ex) {
			
		}
		return null;
	}

}
