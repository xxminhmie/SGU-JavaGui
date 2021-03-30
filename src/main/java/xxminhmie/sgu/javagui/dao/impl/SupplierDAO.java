package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.ISupplierDAO;
import xxminhmie.sgu.javagui.mapper.SupplierMapper;
import xxminhmie.sgu.javagui.model.SupplierModel;

public class SupplierDAO extends AbstractDAO<SupplierModel> implements ISupplierDAO{

	@Override
	public List<SupplierModel> findAll() {
		String sql = "SELECT * FROM supplier;";
		return this.query(sql, new SupplierMapper());
	}

	@Override
	public SupplierModel findOne(Long id) {
		String sql = "SELECT * FROM supplier WHERE id = ?";
		List<SupplierModel> sup = this.query(sql, new SupplierMapper(), id);
		return sup.isEmpty() ? null : sup.get(0);	}

	@Override
	public Long save(SupplierModel supplierModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO supplier ");
		sql.append("(name, phone, email, address) ");
		sql.append("VALUES (?, ?, ?, ?);");
		return this.insert(sql.toString(),
				supplierModel.getName(), 
				supplierModel.getPhone(), supplierModel.getEmail(),
				supplierModel.getAddress());	}

	@Override
	public void update(SupplierModel supplierModel) {
		StringBuilder sql = new StringBuilder("UPDATE supplier SET name = ?, ");
		sql.append("phone = ?, email = ?, address = ? ");
		sql.append("WHERE id = ? ;");
		this.update(sql.toString(),
				supplierModel.getName(),
				supplierModel.getPhone(), supplierModel.getEmail(),
				supplierModel.getAddress(),
				supplierModel.getId());		
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM supplier WHERE id = ?";
		this.update(sql, id);		
	}

}
