package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.AccountModel;

public interface IAccountService {
	List<AccountModel> findAll();
	AccountModel findOne(Long id);
	List<AccountModel> findByUsername(String username);

	Long save(AccountModel model);
	AccountModel update(AccountModel update);
	void delete(Long[] ids);

	List<AccountModel> search(String str);

	int getTotalItem();

}
