package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.StaffDAO;
import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.service.IStaffService;

public class StaffService implements IStaffService{
	StaffDAO dao = new StaffDAO();
	@Override
	public List<StaffModel> findAll() {
		return dao.findAll();
	}

	@Override
	public StaffModel findOne(Long id) {
		StaffModel staffModel = dao.findOne(id);
		return staffModel;
	}

	@Override
	public List<StaffModel> search(String str) {
		List<StaffModel> list = this.findAll();
		List<StaffModel> resultList = new ArrayList<StaffModel>();
		int index = 0;
; 		Boolean flag = false;
		for (StaffModel e : list) {
			flag = false;
			String id = String.valueOf(e.getId());
			String firstName = e.getFirstName().toLowerCase();
			String lastName = e.getLastName().toLowerCase();
			String phone = e.getPhone();
			String email = e.getEmail();
			String address = e.getAddress();
			if(id.contains(str) || firstName.contains(str.toLowerCase()) || lastName.contains(str.toLowerCase()) ||phone.contains(str) || phone.contains(str) || email.contains(str) || address.contains(str.toLowerCase())) {
				flag = true;
			}			
			if(flag==true) {
				resultList.add(e);
			}
		}
		return resultList;
	}

	@Override
	public StaffModel save(StaffModel staffModel) {
		Long staffId = dao.save(staffModel);
		return dao.findOne(staffId);
	}

	@Override
	public StaffModel update(StaffModel updateStaff) {
		dao.update(updateStaff);
		return dao.findOne(updateStaff.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			dao.delete(id);
		}
	}

	
}
