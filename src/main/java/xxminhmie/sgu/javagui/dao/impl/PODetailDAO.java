package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IPODetailDAO;
import xxminhmie.sgu.javagui.mapper.PODetailMapper;
import xxminhmie.sgu.javagui.model.PODetailModel;

public class PODetailDAO extends AbstractDAO<PODetailModel> implements IPODetailDAO{

	@Override
	public List<PODetailModel> findAll() {
		String sql = "SELECT * FROM podetail;";
		return this.query(sql, new PODetailMapper());
	}

	@Override
	public PODetailModel findOne(Long poId, Long skuId) {
		String sql = "SELECT * FROM podetail WHERE poid = ? and skuid = ?;";
		List<PODetailModel> list = this.query(sql, new PODetailMapper(), poId, skuId);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Long save(PODetailModel detailModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO podetail ");
		sql.append("(poid, skuid, supplierid, quantity, unitprice, subtotal)");
		sql.append(" VALUES(?,?,?,?,?,?);");
		return this.insert(sql.toString(), new PODetailMapper(),
				detailModel.getPoId(), detailModel.getSkuId(),
				detailModel.getSupplierId(), detailModel.getQuantity(),
				detailModel.getUnitPrice(), detailModel.getSubTotal());
	}

	@Override
	public void update(PODetailModel updateDetail) {
		StringBuilder sql = new StringBuilder("UPDATE podetail SET supplierid = ?, ");
		sql.append("quantity = ?, unitprice = ?, subtotal = ?");
		sql.append(" WHERE poid = ? and skuid = ?;");
		this.update(sql.toString(), updateDetail.getSupplierId(),
				updateDetail.getQuantity(), updateDetail.getUnitPrice(),
				updateDetail.getSubTotal(), updateDetail.getPoId(),
				updateDetail.getSkuId());
		
	}

	@Override
	public void delete(Long poId, Long skuId) {
		String sql = "DELETE FROM podetail WHERE poid = ? and skuid = ?;";
		this.update(sql, poId, skuId);		
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
