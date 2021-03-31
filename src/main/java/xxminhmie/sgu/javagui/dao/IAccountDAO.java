package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.AccountModel;

public interface IAccountDAO extends GenericDAO<AccountModel>{
	Long save(AccountModel model);
	void update(AccountModel update);
	void delete(Long id);
	
	List<AccountModel> findAll();
	AccountModel findOne(Long id);

	List<AccountModel> findByUsername(String username);
	

}
