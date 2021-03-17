package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.BillModel;

public class BillMapper implements RowMapper<BillModel>{

	@Override
	public BillModel mapRow(ResultSet rs) {
		BillModel bil = new BillModel();
		try {
			bil.setId(rs.getLong("id"));
			bil.setCustomerId(rs.getLong("customerid"));
			bil.setStaffId(rs.getLong("staffid"));
			bil.setCreatedDate(rs.getDate("createddate"));
			bil.setTotal(rs.getString("total"));
			return bil;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
