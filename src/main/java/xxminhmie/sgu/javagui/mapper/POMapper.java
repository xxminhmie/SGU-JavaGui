package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.POModel;

public class POMapper implements RowMapper<POModel> {

	@Override
	public POModel mapRow(ResultSet rs) {
		try {
			POModel po = new POModel();
			po.setId(rs.getLong("id"));
			po.setStaffId(rs.getLong("staffid"));
			po.setSupplierId(rs.getLong("supplierid"));
			po.setCreatedDate(rs.getDate("createddate"));
			po.setTotal(rs.getNString("total"));
			po.setStatus(rs.getNString("status"));
			return po;

		}catch(SQLException e) {
			
		}
		return null;
	}

}
