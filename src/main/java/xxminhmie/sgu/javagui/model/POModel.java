package xxminhmie.sgu.javagui.model;
//DONE
public class POModel extends AbstractModel{
	Long staffId;
	Long supplierId;
	java.sql.Date createdDate;
	String total;
	String status;
	
	
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public java.sql.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
