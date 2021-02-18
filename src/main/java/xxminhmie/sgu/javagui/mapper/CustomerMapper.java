package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.CustomerModel;

public class CustomerMapper implements RowMapper<CustomerModel> {

	@Override
	public CustomerModel mapRow(ResultSet rs) {
		try {
			CustomerModel cus = new CustomerModel();
			cus.setId(rs.getLong("id"));
			cus.setFullName(rs.getString("fullname"));
			cus.setEmail(rs.getString("email"));
			cus.setPhone(rs.getString("phone"));
			cus.setDob(rs.getDate("dob"));
			return cus;
		}catch(SQLException e) {
			return null;
		}	
	}

}
