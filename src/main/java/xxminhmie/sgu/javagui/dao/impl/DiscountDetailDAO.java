package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IDiscountDetailDAO;
import xxminhmie.sgu.javagui.mapper.DiscountDetailMapper;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public class DiscountDetailDAO extends AbstractDAO<DiscountDetailModel> implements IDiscountDetailDAO {

	@Override
	public List<DiscountDetailModel> findAll() {
		String sql = "SELECT * FROM discountdetail;";
		return this.query(sql, new DiscountDetailMapper());
	}

	@Override
	public List<DiscountDetailModel> findOne(Long discountId, Long skuId) {
		String sql = "SELECT * FROM discountdetail WHERE discountid = ? and skuId = ? ";
		List<DiscountDetailModel> list = this.query(sql, new DiscountDetailMapper(), discountId, skuId);
		return list;
	}

	@Override
	public Long save(DiscountDetailModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO discountdetail ");
		sql.append(" (discountid, skuid, rate, status) ");
		sql.append("VALUES (?,?,?,?);");
		return this.insert(sql.toString(), model.getDiscountId(), model.getSkuId(), model.getRate(), model.getStatus());
	}

	@Override
	public void update(DiscountDetailModel update) {
		StringBuilder sql = new StringBuilder("UPDATE discountdetail SET ");
		sql.append("rate = ?, status  = ? ");
		sql.append("WHERE discountid = ? and skuid = ?");
		this.update(sql.toString(), update.getRate(), update.getStatus(), update.getDiscountId(), update.getSkuId());

	}

	@Override
	public void delete(Long discountId, Long skuId) {
		String sql = "DELETE * FROM discountdetail WHERE discountid = ? and skuid = ?";
		this.update(sql, discountId, skuId);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
