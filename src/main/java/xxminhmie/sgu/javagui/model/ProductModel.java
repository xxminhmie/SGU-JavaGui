package xxminhmie.sgu.javagui.model;
//DONE
public class ProductModel extends AbstractModel{
	protected String name;
	protected String brand;
	protected String description;
	protected String status;
	public ProductModel() {
		
	}
	public ProductModel(Long id,String name, String brand, String description, String status) {
		super(id);
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.status = status;

	}
	
	public ProductModel(Long id,String name, String brand, String status) {
		super(id);
		this.name = name;
		this.brand = brand;
		this.description = "";
		this.status = status;

	}
	
	public ProductModel(String name, String brand, String description) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.status = "Actived";

	}
	
	public ProductModel(String name, String brand) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = "";
		this.status = "Actived";

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
