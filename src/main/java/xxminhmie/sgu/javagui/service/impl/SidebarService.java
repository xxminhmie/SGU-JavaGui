package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.SidebarDAO;
import xxminhmie.sgu.javagui.model.SidebarModel;
import xxminhmie.sgu.javagui.service.ISidebarService;

public class SidebarService implements ISidebarService {

	SidebarDAO dao = new SidebarDAO();
	@Override
	public List<SidebarModel> findAll() {
		return dao.findAll();
	}

}
