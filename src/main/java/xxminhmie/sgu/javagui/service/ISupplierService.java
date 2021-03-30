package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.SupplierModel;

public interface ISupplierService {
	List<SupplierModel> findAll();

	SupplierModel findOne(Long id);

	List<SupplierModel> search(String str);

	SupplierModel save(SupplierModel model);

	SupplierModel update(SupplierModel updateModel);

	void delete(Long[] ids);
}
