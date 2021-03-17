package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.AccountDAO;
import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.service.IAccountService;

public class AccountService implements IAccountService{

	AccountDAO accDao = new AccountDAO();
	
	@Override
	public List<AccountModel> findByUsername(String username) {
		return accDao.findByUsername(username);
	}

}
