package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.DiscountModel;

public class DiscountMapper implements RowMapper<DiscountModel>{

	@Override
	public DiscountModel mapRow(ResultSet rs) {
		try {
			DiscountModel model = new DiscountModel();
			model.setId(rs.getLong("id"));
			model.setName(rs.getNString("name"));
			model.setStartDate(rs.getDate("startdate"));
			model.setEndDate(rs.getDate("enddate"));
			model.setDescription(rs.getNString("description"));
			model.setStatus(rs.getNString("status"));
			return model;
		}catch(SQLException ex) {
			
		}
		return null;
	}

}
