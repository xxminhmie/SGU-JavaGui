package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import javax.inject.Inject;

import xxminhmie.sgu.javagui.dao.ICustomerDAO;
import xxminhmie.sgu.javagui.dao.impl.CustomerDAO;
import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.paging.Pageble;
import xxminhmie.sgu.javagui.service.ICustomerService;

public class CustomerService implements ICustomerService {
//	@Inject
	protected CustomerDAO cusDao = new CustomerDAO();

	@Override
	public List<CustomerModel> findAll(Pageble pageble) {
		return cusDao.findAll(pageble);
	}

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
	public CustomerModel save(CustomerModel customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel update(CustomerModel updateCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
