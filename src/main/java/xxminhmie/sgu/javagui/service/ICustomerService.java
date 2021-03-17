package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.paging.Pageble;

public interface ICustomerService {
	List<CustomerModel> findAll(Pageble pageble);
	
	List<CustomerModel> findAll();
	
	List<CustomerModel> search(String str);

	CustomerModel findOne(Long id);
	List<CustomerModel> findByPhone(String phone);
	
	CustomerModel save(CustomerModel customer);
	CustomerModel update(CustomerModel updateCustomer);
	void delete(Long[] ids);
	
	int getTotalItem();

	
}
