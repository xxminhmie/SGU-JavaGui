package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.StaffModel;

public interface IStaffService {
	List<StaffModel> findAll();
	
	StaffModel findOne(Long id);
	List<StaffModel> search(String str);

	StaffModel save(StaffModel staffModel);
	StaffModel update(StaffModel updateStaff);
	void delete(Long[] ids);
	
}
