package xxminhmie.sgu.javagui.model;

import java.sql.Date;

public class StaffModel extends AbstractModel{
	protected String firstName;
	protected String lastName;
	protected String phone;
	protected String email;
	protected java.sql.Date dob;
	protected String address;
	
	
	
	public StaffModel() {
		super();
	}
	public StaffModel(Long id,String firstName, String lastName, String phone, String email, Date dob, String address) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
		this.address = address;
	}
	public StaffModel(String firstName, String lastName, String phone, String email, Date dob, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
		this.address = address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
