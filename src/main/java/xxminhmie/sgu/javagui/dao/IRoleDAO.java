package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.RoleModel;

public interface IRoleDAO extends GenericDAO<RoleModel>{
	List<RoleModel> findAll();

	RoleModel findOne(Long id);
	
	Long save(RoleModel model);
	void update(RoleModel update);
	void delete(Long id);
	
	int getTotalItem();

}
