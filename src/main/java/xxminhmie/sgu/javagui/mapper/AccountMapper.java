package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.AccountModel;

public class AccountMapper implements RowMapper<AccountModel>{

	@Override
	public AccountModel mapRow(ResultSet rs) {
		try {
			AccountModel model = new AccountModel();
			model.setId(rs.getLong("id"));
			model.setRoleId(rs.getLong("roleid"));
			model.setStaffId(rs.getLong("staffid"));
			model.setUsername(rs.getString("username"));
			model.setPassword(rs.getString("password"));
			return model;
		}catch(SQLException e) {
			return null;

		}
	}

}
