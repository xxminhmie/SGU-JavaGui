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
			sku.setSellPrice(rs.getString("sellprice"));
			sku.setImage(rs.getString("image"));
			sku.setStatus(rs.getString("status"));
			sku.setCreatedDate(rs.getDate("createddate"));
			sku.setCreatedBy(rs.getString("createdBy"));
			if (rs.getDate("modifieddate") != null) {
				sku.setModifiedDate(rs.getDate("modifieddate"));
			}
			if (rs.getString("modifiedby") != null) {
				sku.setModifiedBy(rs.getString("modifiedby"));
			}
			return sku;
		}catch(SQLException e) {
			return null;
		}
	}

}
