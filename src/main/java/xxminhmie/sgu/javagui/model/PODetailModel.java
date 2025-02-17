package xxminhmie.sgu.javagui.model;

public class PODetailModel{
	protected Long poId;
	protected Long skuId;
	protected Long supplierId;
	protected int quantity;
	protected String unitPrice;
	protected String subTotal;
	
	
	
	public PODetailModel(Long poId, Long skuId, Long supplierId, int quantity, String unitPrice, String subTotal) {
		super();
		this.poId = poId;
		this.skuId = skuId;
		this.supplierId = supplierId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
	}
	public PODetailModel() {
		super();
	}
	
	public Long getPoId() {
		return poId;
	}
	public void setPoId(Long poId) {
		this.poId = poId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	@Override
	public String toString() {
		return "PODetailModel [poId=" + poId + ", skuId=" + skuId + ", supplierId=" + supplierId + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + ", subTotal=" + subTotal + "]";
	}

	
	
}
