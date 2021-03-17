package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.SkuModel;

public class SkuMapper implements RowMapper<SkuModel>{

	@Override
	public SkuModel mapRow(ResultSet rs) {
		try {
			SkuModel sku = new SkuModel();
			sku.setId(rs.getLong("id"));
			sku.setColor(rs.getString("color"));
			sku.setSize(rs.getString("size"));
			sku.setQuantity(rs.getInt("quantity"));
			sku.setPrice(rs.getString("price"));
			sku.setImportPrice(rs.getString("sellprice"));
			sku.setImage(rs.getString("image"));
			sku.setStatus(rs.getString("status"));
			return sku;
		}catch(SQLException e) {
			return null;
		}
	}

}
