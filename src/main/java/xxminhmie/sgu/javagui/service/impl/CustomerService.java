package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xxminhmie.sgu.javagui.dao.ICustomerDAO;
import xxminhmie.sgu.javagui.dao.impl.CustomerDAO;
import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.service.ICustomerService;

public class CustomerService implements ICustomerService {
//	@Inject
	protected CustomerDAO cusDao = new CustomerDAO();

//	@Override
//	public List<CustomerModel> findAll(Pageble pageble) {
//		return cusDao.findAll(pageble);
//	}

	@Override
	public List<CustomerModel> findAll() {
		return cusDao.findAll();
	}
	@Override
	public CustomerModel findOne(Long id) {
		CustomerModel cusModel = cusDao.findOne(id);
		return cusModel;
	}

	@Override
	public List<CustomerModel> findByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel save(CustomerModel customerModel) {
		Long customerId = cusDao.save(customerModel);
		return cusDao.findOne(customerId);
	}

	@Override
	public CustomerModel update(CustomerModel updateCustomer) {
		cusDao.update(updateCustomer);
		return cusDao.findOne(updateCustomer.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			cusDao.delete(id);
		}
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<CustomerModel> search(String str) {
		List<CustomerModel> list = this.findAll();
		List<CustomerModel> resultList = new ArrayList<CustomerModel>();
		for (CustomerModel e : list) {
			String id = String.valueOf(e.getId());
			String firstName = e.getFirstName().toLowerCase();
			String lastName = e.getLastName().toLowerCase();
			String phone = e.getPhone();
			String email = e.getEmail();
			if(id.contains(str) || firstName.contains(str.toLowerCase()) || lastName.contains(str.toLowerCase()) ||phone.contains(str) || phone.contains(str) || email.contains(str)) {
				resultList.add(e);
			}			
		}
		return resultList;
	}
}
