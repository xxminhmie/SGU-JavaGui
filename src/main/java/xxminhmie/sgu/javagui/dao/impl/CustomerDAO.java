package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import xxminhmie.sgu.javagui.dao.ICustomerDAO;
import xxminhmie.sgu.javagui.mapper.CustomerMapper;
import xxminhmie.sgu.javagui.model.CustomerModel;
import xxminhmie.sgu.javagui.paging.Pageble;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO {

	@Override
	public List<CustomerModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM customer");
		if(pageble.getSorter()!=null
			&& StringUtils.isNoneBlank((pageble.getSorter().getSortName())) && StringUtils.isNoneBlank(pageble.getSorter().getSortBy()))
		{
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
		}
		if(pageble.getOffset()!=null && pageble.getLimit()!=null) {
			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
		}
		return this.query(sql.toString(),new CustomerMapper());	
	}
	@Override
	public List<CustomerModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM customer");
		return this.query(sql.toString(),new CustomerMapper());	
	}

	@Override
	public CustomerModel findOne(Long id) {
		String sql = "SELECT * FROM customer WHERE id = ?";
		List<CustomerModel> cus = this.query(sql, new CustomerMapper(), id);
		return cus.isEmpty() ? null : cus.get(0);//get id
	}

	@Override
	public List<CustomerModel> findByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(CustomerModel customerModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO customer");
		sql.append("(firstname, lastname, phone, email, dob) ");
		sql.append("VALUES (?, ?, ?, ?, ?);");
		return this.insert(sql.toString(),
				customerModel.getFirstName(), customerModel.getLastName(), 
				customerModel.getPhone(), customerModel.getEmail(), 
				customerModel.getDob());
	}

	@Override
	public void update(CustomerModel updateCustomer) {
		StringBuilder sql = new StringBuilder("UPDATE customer SET firstname = ?, lastname = ?, phone = ?,");
		sql.append(" email = ?, dob = ?");
//		sql.append("createddate = ?, modifieddate = ?");
		sql.append(" WHERE id = ?");
		this.update(sql.toString(), 
				updateCustomer.getFirstName(), updateCustomer.getLastName(),  
				updateCustomer.getPhone(), updateCustomer.getEmail(), 
//				updateCustomer.getCreatedDate(), updateCustomer.getModifiedDate(),
				updateCustomer.getDob(), updateCustomer.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM customer WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
