package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.CustomerModel;

public interface ICustomerDAO extends GenericDAO<CustomerModel>{
	
	List<CustomerModel> findAll();

	CustomerModel findOne(Long id);
	List<CustomerModel> findByPhone(String phone);
	
	Long save(CustomerModel customerModel);
	void update(CustomerModel updateCustomer);
	void delete(Long id);
	
	int getTotalItem();
}
