package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IStaffDAO;
import xxminhmie.sgu.javagui.mapper.StaffMapper;
import xxminhmie.sgu.javagui.model.StaffModel;

public class StaffDAO extends AbstractDAO<StaffModel> implements IStaffDAO{

	@Override
	public List<StaffModel> findAll() {
		String sql = "SELECT * FROM staff";
		return this.query(sql, new StaffMapper());
	}

	@Override
	public StaffModel findOne(Long id) {
		String sql = "SELECT * FROM staff WHERE id = ?";
		List<StaffModel> staff = this.query(sql, new StaffMapper(), id);
		return staff.isEmpty() ? null : staff.get(0);
	}

	@Override
	public Long save(StaffModel staffModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO staff");
		sql.append("(firstname, lastname, phone, email, dob, address) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?);");
		return this.insert(sql.toString(),
				staffModel.getFirstName(), staffModel.getLastName(),
				staffModel.getPhone(), staffModel.getEmail(),
				staffModel.getDob(), staffModel.getAddress());
	}

	@Override
	public void update(StaffModel staffModel) {
		StringBuilder sql = new StringBuilder("UPDATE staff SET firstname = ?, lastname = ?,");
		sql.append("phone = ?, email = ?, dob = ?, address = ? ");
		sql.append("WHERE id = ? ;");
		this.update(sql.toString(),
				staffModel.getFirstName(), staffModel.getLastName(),
				staffModel.getPhone(), staffModel.getEmail(),
				staffModel.getDob(), staffModel.getAddress(),
				staffModel.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM staff WHERE id = ?";
		this.update(sql, id);
		
	}

}
