package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IAccountDAO;
import xxminhmie.sgu.javagui.mapper.AccountMapper;
import xxminhmie.sgu.javagui.model.AccountModel;

public class AccountDAO extends AbstractDAO<AccountModel> implements IAccountDAO{

	@Override
	public Long save(AccountModel accountModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AccountModel accountModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountModel> findByUsername(String username) {
		String sql = "SELECT * FROM account WHERE username = ?";
		return this.query(sql, new AccountMapper(), username);
	}

}
