package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.BillDetailModel;

public class BillDetailMapper implements RowMapper<BillDetailModel>{

	@Override
	public BillDetailModel mapRow(ResultSet rs) {
		try {
			BillDetailModel model = new BillDetailModel();
			model.setBillId(rs.getLong("billid"));
			model.setSkuId(rs.getLong("skuid"));
			model.setDiscountId(rs.getLong("discountid"));
			model.setQuantity(rs.getInt("quantity"));
			model.setSubTotal(rs.getNString("subtotal"));
			return model;
		}catch(SQLException ex) {
			
		}
		return null;
	}

}
