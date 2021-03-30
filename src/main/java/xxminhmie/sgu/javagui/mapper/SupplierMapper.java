package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.SupplierModel;

public class SupplierMapper implements RowMapper<SupplierModel> {

	@Override
	public SupplierModel mapRow(ResultSet rs) {
		try {
			SupplierModel model = new SupplierModel();
			model.setId(rs.getLong("id"));
			model.setName(rs.getString("name"));
			model.setEmail(rs.getString("email"));
			model.setAddress(rs.getString("address"));
			model.setPhone(rs.getString("phone"));
			return model;
		}catch(SQLException e) {
			return null;
		}
	}

}
