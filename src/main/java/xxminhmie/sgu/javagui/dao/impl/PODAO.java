package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IPODAO;
import xxminhmie.sgu.javagui.mapper.POMapper;
import xxminhmie.sgu.javagui.model.POModel;

public class PODAO extends AbstractDAO<POModel> implements IPODAO {

	@Override
	public List<POModel> findAll() {
		String sql = "SELECT * FROM po";
		return this.query(sql.toString(), new POMapper());
	}

	@Override
	public POModel findOne(Long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM  po WHERE id = ?";
		List<POModel> po = this.query(sql, new POMapper(), id);
		return po.isEmpty() ? null : po.get(0);// get id }
	}

	@Override
	public POModel findOneByBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(POModel poModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO po ");
		sql.append("(staffid, createddate,total, status) ");
		sql.append("VALUES (?, ?, ?, ?)");
		return this.insert(sql.toString(),
				poModel.getStaffId(), 
				poModel.getCreatedDate(), poModel.getTotal(),
				poModel.getStatus());
	}

	@Override
	public void update(POModel poModel) {
		StringBuilder sql = new StringBuilder("UPDATE po SET ");
		sql.append("staffid = ?, createddate = ?, total = ?, status = ? ");
		sql.append("WHERE id = ?");
		this.update(sql.toString(),
				poModel.getStaffId(), 
				poModel.getCreatedDate(), poModel.getTotal(),
				poModel.getStatus(), poModel.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM po WHERE id = ?";
		this.update(sql, id);
	}

}
