package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;

import xxminhmie.sgu.javagui.model.SidebarModel;

public class SidebarMapper implements RowMapper<SidebarModel>{

	@Override
	public SidebarModel mapRow(ResultSet rs) {
		try {
			SidebarModel side = new SidebarModel();
			side.setId(rs.getLong("id"));
			side.setName(rs.getNString("name"));
			side.setRoleid(rs.getLong("roleid"));
			return side;
		}catch(java.sql.SQLException e) {
			return null;
		}
	}

}
