package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.ISidebarDAO;
import xxminhmie.sgu.javagui.mapper.SidebarMapper;
import xxminhmie.sgu.javagui.model.SidebarModel;

public class SidebarDAO extends AbstractDAO<SidebarModel> implements ISidebarDAO{

	@Override
	public List<SidebarModel> findAll() {
		String sql = "SELECT * FROM sidebar";
		return this.query(sql.toString(), new SidebarMapper());
	}

}
