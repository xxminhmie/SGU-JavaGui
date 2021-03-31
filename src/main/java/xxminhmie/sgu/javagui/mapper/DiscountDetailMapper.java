package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public class DiscountDetailMapper implements RowMapper<DiscountDetailModel>{

	@Override
	public DiscountDetailModel mapRow(ResultSet rs) {
		try {
			DiscountDetailModel model = new DiscountDetailModel();
			model.setDiscountId(rs.getLong("discountid"));
			model.setSkuId(rs.getLong("skuid"));
			model.setRate(rs.getInt("rate"));
			model.setStatus(rs.getNString("status"));
			return model;
		}catch(SQLException ex) {
			
		}
		return null;
	}

}
