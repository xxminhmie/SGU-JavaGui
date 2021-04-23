package xxminhmie.sgu.javagui.model;
//DONE
public class DiscountDetailModel {
	Long discountId;
	Long skuId;
	int rate;
	String status;
	
	public DiscountDetailModel(Long discountId, Long skuId, int rate, String status) {
		super();
		this.discountId = discountId;
		this.skuId = skuId;
		this.rate = rate;
		this.status = status;
	}
	
	public DiscountDetailModel() {
		super();
	}

	public Long getDiscountId() {
		return discountId;
	}
	public void setDiscountId(Long discountId) {
		this.discountId = discountId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
