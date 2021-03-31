package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.RoleModel;


public interface IRoleService {
	List<RoleModel> findAll();
	RoleModel findOne(Long id);
	
	RoleModel save(RoleModel model);
	void update(RoleModel update);
	void delete(Long[] ids);
	
	List<RoleModel> search(String str);
}
