package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IBillDAO;
import xxminhmie.sgu.javagui.mapper.BillMapper;
import xxminhmie.sgu.javagui.model.BillModel;

public class BillDAO extends AbstractDAO<BillModel> implements IBillDAO {

	@Override
	public List<BillModel> findAll() {
		String sql = "SELECT * FROM bill;";
		return this.query(sql, new BillMapper());
	}

	@Override
	public BillModel findOne(Long id) {
		String sql = "SELECT * FROM bill WHERE id = ?";
		List<BillModel> list = this.query(sql, new BillMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Long save(BillModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO bill ");
		sql.append("(staffid, customerid, createddate, total) ");
		sql.append(" VALUES(?,?,?,?)");
		return this.insert(sql.toString(), model.getStaffId(), model.getCustomerId(),
				model.getCreatedDate(), model.getTotal());
	}

	@Override
	public void update(BillModel update) {
		StringBuilder sql = new StringBuilder("UPDATE bill SET staffid = ?, ");
		sql.append(" customerid = ?, createddate = ?, total = ?");
		sql.append(" WHERE id = ?;");
		this.update(sql.toString(), update.getStaffId(), update.getCustomerId(),
				update.getCreatedDate(), update.getTotal(), update.getId());		
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM bill WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
