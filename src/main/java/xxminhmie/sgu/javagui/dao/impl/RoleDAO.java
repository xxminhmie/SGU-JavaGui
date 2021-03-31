package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IRoleDAO;
import xxminhmie.sgu.javagui.mapper.RoleMapper;
import xxminhmie.sgu.javagui.model.RoleModel;

public class RoleDAO extends AbstractDAO<RoleModel> implements IRoleDAO{

	@Override
	public List<RoleModel> findAll() {
		String sql = "SELECT * FROM role";
		return this.query(sql, new RoleMapper());
	}

	@Override
	public RoleModel findOne(Long id) {
		String sql = "SELECT * FROM role WHERE id = ?";
		List<RoleModel> list = this.query(sql, new RoleMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}


	@Override
	public Long save(RoleModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO role ");
		sql.append("(name, description) VALUES (?,?)");
		return this.insert(sql.toString(), model.getName(), model.getDescription());
	}

	@Override
	public void update(RoleModel update) {
		String sql = "UPDATE role SET name = ?, description = ? WHERE id = ?";
		this.update(sql, update.getName(), update.getDescription(), update.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE * FROM role WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
