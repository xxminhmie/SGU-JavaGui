package xxminhmie.sgu.javagui.model;

import java.sql.Date;

public class CustomerModel extends AbstractModel<CustomerModel>{
	protected String fullName;
	protected String phone;
	protected String email;
	protected java.sql.Date dob;
	
	
	public CustomerModel() {
		super();
	}
	public CustomerModel(Long id,String fullName, String phone, String email, Date dob) {
		super(id);
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}
	public CustomerModel(String fullName, String phone, String email, Date dob) {
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.sql.Date getDob() {
		return dob;
	}
	public void setDob(java.sql.Date dob) {
		this.dob = dob;
	}
	
	
	
	

}
