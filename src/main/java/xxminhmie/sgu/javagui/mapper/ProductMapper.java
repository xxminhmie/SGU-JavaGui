package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.ProductModel;

public class ProductMapper implements RowMapper<ProductModel>{

	@Override
	public ProductModel mapRow(ResultSet rs) {
		ProductModel pro = new ProductModel();
		try {
			pro.setId(rs.getLong("id"));
			pro.setName(rs.getString("name"));
			pro.setBrand(rs.getString("brand"));
			pro.setDescription(rs.getString("description"));
			pro.setStatus(rs.getString("status"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}

	

}
