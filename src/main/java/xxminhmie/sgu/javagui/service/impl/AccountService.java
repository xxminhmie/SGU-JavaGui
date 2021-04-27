package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.AccountDAO;
import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.service.IAccountService;

public class AccountService implements IAccountService {

	AccountDAO dao = new AccountDAO();

	@Override
	public List<AccountModel> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<AccountModel> findAll() {
		return dao.findAll();
	}

	@Override
	public AccountModel findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Long save(AccountModel model) {
		return dao.save(model);
	}

	@Override
	public AccountModel update(AccountModel update) {
		dao.update(update);
		return dao.findOne(update.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			dao.delete(id);
		}
	}

	@Override
	public List<AccountModel> search(String str) {
		List<AccountModel> list = this.findAll();
		List<AccountModel> resultList = new ArrayList<AccountModel>();
		for (AccountModel e : list) {
			String id = String.valueOf(e.getId());
			String roleId = String.valueOf(e.getRoleId());
			String staffId = String.valueOf(e.getStaffId());
			String username = e.getUsername().toLowerCase();

			if (id.contains(str) || roleId.contains(str.toLowerCase()) || staffId.contains(str.toLowerCase())
					|| username.contains(str)) {
				resultList.add(e);
			}

		}
		return resultList;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
