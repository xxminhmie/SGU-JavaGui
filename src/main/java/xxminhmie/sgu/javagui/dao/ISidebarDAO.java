package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.SidebarModel;

public interface ISidebarDAO extends GenericDAO<SidebarModel>{

	List<SidebarModel> findAll();

}
