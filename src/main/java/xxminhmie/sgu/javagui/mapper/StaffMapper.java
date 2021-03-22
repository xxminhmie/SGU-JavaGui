package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.StaffModel;

public class StaffMapper implements RowMapper<StaffModel>{

	@Override
	public StaffModel mapRow(ResultSet rs) {
		StaffModel sta = new StaffModel();
		try {
			sta.setId(rs.getLong("id"));
			sta.setFirstName(rs.getString("firstname"));
			sta.setLastName(rs.getString("lastname"));
			sta.setEmail(rs.getString("email"));
			sta.setPhone(rs.getString("phone"));
			sta.setAddress(rs.getString("address"));
			sta.setDob(rs.getDate("dob"));
			return sta;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
