package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.PODetailModel;

public class PODetailMapper implements RowMapper<PODetailModel> {

	@Override
	public PODetailModel mapRow(ResultSet rs) {
		try {
			PODetailModel detail = new PODetailModel();
			detail.setPoId(rs.getLong("poid"));
			detail.setSkuId(rs.getLong("skuid"));
			detail.setSupplierId(rs.getLong("supplierid"));
			detail.setQuantity(rs.getInt("quantity"));
			detail.setUnitPrice(rs.getNString("unitprice"));
			detail.setSubTotal(rs.getNString("subtotal"));
			return detail;

		} catch (SQLException e) {
			return null;

		}
	}

}
