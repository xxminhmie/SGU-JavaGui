package xxminhmie.sgu.javagui.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import xxminhmie.sgu.javagui.model.ReportModel;

public class ReportMapper  implements RowMapper<ReportModel>{

	@Override
	public ReportModel mapRow(ResultSet rs) {
		ReportModel re = new ReportModel();
		try {
			re.setProductId(rs.getLong("id"));
			re.setQuantity(rs.getInt("SUM"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}
}
