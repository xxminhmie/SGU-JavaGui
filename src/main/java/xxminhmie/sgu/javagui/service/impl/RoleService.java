package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.RoleDAO;
import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.IRoleService;

public class RoleService implements IRoleService {
	RoleDAO dao = new RoleDAO();
	@Override
	public List<RoleModel> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public RoleModel findOne(Long id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public RoleModel save(RoleModel model) {
		Long id = dao.save(model);
		return dao.findOne(id);
	}

	@Override
	public void update(RoleModel update) {
		 dao.update(update);;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoleModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
