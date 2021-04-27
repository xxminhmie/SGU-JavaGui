package xxminhmie.sgu.javagui.model;

public class ReportModel {
	Long productId;
	int quantity;
	String total;
	public ReportModel(Long productId, int quantity, String total) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.total = total;
	}
	public ReportModel() {
		
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	

}
