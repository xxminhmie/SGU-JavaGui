package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IAccountDAO;
import xxminhmie.sgu.javagui.mapper.AccountMapper;
import xxminhmie.sgu.javagui.model.AccountModel;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccountDAO {

	@Override
	public Long save(AccountModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO account ");
		sql.append(" (staffid, roleid, username, password) ");
		sql.append(" VALUES (?,?,?,?);");
		return this.insert(sql.toString(), new AccountMapper(), model.getStaffId(), model.getId(), model.getUsername(),
				model.getPassword());
	}

	@Override
	public void update(AccountModel update) {
		StringBuilder sql = new StringBuilder(
				"UPDATE account SET staffid = ?, roleid = ?, username = ?, password = ? ");
		sql.append("WHERE id = ?");
		this.update(sql.toString(), update.getStaffId(), update.getStaffId(), update.getUsername(),
				update.getPassword(), update.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE * FROM account WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public List<AccountModel> findAll() {
		String sql = "SELECT * FROM account";
		return this.query(sql, new AccountMapper());
	}

	@Override
	public List<AccountModel> findByUsername(String username) {
		String sql = "SELECT * FROM account WHERE username = ?";
		return this.query(sql, new AccountMapper(), username);
	}

	@Override
	public AccountModel findOne(Long id) {
		String sql = "SELECT * FROM account WHERE id = ";
		List<AccountModel> list = this.query(sql, new AccountMapper(), id);
		return list.isEmpty() ? null : list.get(0);
	}

}
