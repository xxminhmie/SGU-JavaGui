package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.SupplierDAO;
import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.model.SupplierModel;
import xxminhmie.sgu.javagui.service.ISupplierService;

public class SupplierService implements ISupplierService {
	SupplierDAO dao = new SupplierDAO();

	@Override
	public List<SupplierModel> findAll() {
		return dao.findAll();
	}

	@Override
	public SupplierModel findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public SupplierModel save(SupplierModel model) {
		Long supplierId = dao.save(model);
		return dao.findOne(supplierId);
	}

	@Override
	public SupplierModel update(SupplierModel updateModel) {
		dao.update(updateModel);
		return dao.findOne(updateModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			dao.delete(id);
		}
	}

	@Override
	public List<SupplierModel> search(String str) {
		// TODO Auto-generated method stub
		List<SupplierModel> list = this.findAll();
		List<SupplierModel> resultList = new ArrayList<SupplierModel>();
		for (SupplierModel e : list) {
			String id = String.valueOf(e.getId());
			String firstName = e.getName().toLowerCase();
			String phone = e.getPhone();
			String email = e.getEmail();
			String address = e.getAddress();
			if (id.contains(str) || firstName.contains(str.toLowerCase()) || phone.contains(str) || phone.contains(str)
					|| email.contains(str) || address.contains(str.toLowerCase())) {
				resultList.add(e);
			}

		}
		return resultList;
	}

}
