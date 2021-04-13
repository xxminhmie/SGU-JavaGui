package xxminhmie.sgu.javagui.model;

public class SkuModel extends AbstractModel{	
	protected String color;
	protected String size;
	
	protected int quantity;
	
	protected String price;
	protected String importPrice;
	
	protected String image;
	protected String status;
	
	protected Long productId;
	
	public SkuModel() {
		
	}

	public SkuModel(String color, String size, int quantity, String price, String importPrice, String image,
			String status, Long productId) {
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.importPrice = importPrice;
		this.image = image;
		this.status = status;
		this.productId = productId;
	}
	
	public SkuModel(Long id,String color, String size, int quantity, String price, String importPrice, String image,
			String status, Long productId) {
		super(id);
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.importPrice = importPrice;
		this.image = image;
		this.status = status;
		this.productId = productId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(String sellPrice) {
		this.importPrice = sellPrice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SkuModel [color=" + color + ", size=" + size + ", quantity=" + quantity + ", price=" + price
				+ ", importPrice=" + importPrice + ", image=" + image + ", status=" + status + ", productId="
				+ productId + "]";
	}
	
}

