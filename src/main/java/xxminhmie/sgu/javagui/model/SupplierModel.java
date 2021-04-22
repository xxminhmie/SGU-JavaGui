package xxminhmie.sgu.javagui.model;

public class SupplierModel extends AbstractModel {
	String name;
	String email;
	String phone;
	String address;


	public SupplierModel(String name, String email, String phone, String address) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	

	public SupplierModel(Long id, String name, String email, String phone, String address) {
		super(id);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public SupplierModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
