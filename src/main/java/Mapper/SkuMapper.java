//package Mapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import DTO.SkuDTO;
//
//public class SkuMapper implements RowMapper<SkuDTO>{
//
//	@Override
//	public SkuDTO rowMap(ResultSet rs) {
//		try {
//			SkuDTO sku = new SkuDTO();
//			
//			sku.setId(rs.getLong("id"));
//			sku.setColor(rs.getString("color"));
//			sku.setSize(rs.getString("size"));
//			sku.setPrice(rs.getS);
//;			
//		}catch(SQLException e) {
//			
//		}
//		return null;
//	}
//	
//
//}
