package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.AccountModel;

public interface IAccountDAO extends GenericDAO<AccountModel>{
	Long save(AccountModel accountModel);
	void update(AccountModel accountModel);
	void delete(Long id);
	
	List<AccountModel> findAll();
	List<AccountModel> findByUsername(String username);
	

}
