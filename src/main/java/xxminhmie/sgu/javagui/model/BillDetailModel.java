package xxminhmie.sgu.javagui.model;
//DONE
public class BillDetailModel{
	protected Long billId;
	protected Long skuId;
	protected Long discountId;
	protected int quantity;
	protected String subTotal;
	
	
	public BillDetailModel() {
		super();
	}
	public BillDetailModel(Long billId, Long skuId, Long discountId, int quantity, String subTotal) {
		super();
		this.billId = billId;
		this.skuId = skuId;
		this.discountId = discountId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	
	public Long getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
	
}
