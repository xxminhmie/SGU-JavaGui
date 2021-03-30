package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.SupplierModel;

public interface ISupplierDAO extends GenericDAO<SupplierModel>{
	List<SupplierModel> findAll();
	SupplierModel findOne(Long id);
	
	Long save(SupplierModel supplierModel);
	void update(SupplierModel supplierModel);
	void delete(Long id);
}
