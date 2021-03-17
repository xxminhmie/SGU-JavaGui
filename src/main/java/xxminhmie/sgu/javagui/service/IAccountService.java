package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.AccountModel;

public interface IAccountService {
	List<AccountModel> findByUsername(String username);


}
