package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.dao.IBillDetailDAO;
import xxminhmie.sgu.javagui.mapper.BillDetailMapper;
import xxminhmie.sgu.javagui.model.BillDetailModel;

public class BillDetailDAO extends AbstractDAO<BillDetailModel> implements IBillDetailDAO {

	@Override
	public List<BillDetailModel> findAll() {
		String sql = "SELECT * FROM billdeatil";
		return this.query(sql.toString(), new BillDetailMapper());
	}

	@Override
	public List<BillDetailModel> findOne(Long billId, Long skuId) {
		String sql = "SELECT * FROM WHERE billid = ? and sku = ?";
		return this.query(sql, new BillDetailMapper(), billId, skuId);
	}

	@Override
	public Long save(BillDetailModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO billdetail ");
		sql.append("(billid, skuid, discountid, quantity, subtotal) ");
		sql.append(" VALUES(?,?,?,?,?);");
		return this.insert(sql.toString(), model.getBillId(), model.getSkuId(), model.getDiscountId(),
				model.getQuantity(), model.getSubTotal());
	}

	@Override
	public void update(BillDetailModel update) {
		StringBuilder sql = new StringBuilder("UPDATE billdetail SET discountid = ?, ");
		sql.append("quantity = ?, subtotal = ? ");
		sql.append("WHERE billid = ? and skuid = ?");
		this.update(sql.toString(), update.getDiscountId(), update.getQuantity(), update.getSubTotal(),
				update.getBillId(), update.getSkuId());
	}

	@Override
	public void delete(Long billId, Long skuId) {
		String sql = "DELETE * FROM billdetail WHERE billid = ? and skuid = ?";
		this.update(sql.toString(), billId, skuId);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
