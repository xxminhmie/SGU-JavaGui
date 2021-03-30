package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.StaffModel;

public interface IStaffDAO extends GenericDAO<StaffModel>{
	List<StaffModel> findAll();
	StaffModel findOne(Long id);
	
	Long save(StaffModel staffModel);
	void update(StaffModel staffModel);
	void delete(Long id);
	
	

}
