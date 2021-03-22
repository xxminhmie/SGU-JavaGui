package xxminhmie.sgu.javagui.model;

import java.sql.Date;

public class CustomerModel extends AbstractModel{
	protected String firstName;
	protected String lastName;
	protected String phone;
	protected String email;
	protected java.sql.Date dob;
	
	
	public CustomerModel() {
		super();
	}
	public CustomerModel(Long id,String firstName,String lastName, String phone, String email, Date dob) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}
	public CustomerModel(String firstName,String lastName, String phone, String email, Date dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
