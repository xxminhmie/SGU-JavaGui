package xxminhmie.sgu.javagui.model;

import java.sql.Date;

//DONE
public class POModel extends AbstractModel{
	Long staffId;
	java.sql.Date createdDate;
	String total;
	String status;
	
	public POModel() {
		
	}
	public POModel(Long id, Long staffId, Date createdDate, String total, String status) {
		super(id);
		this.staffId = staffId;
		this.createdDate = createdDate;
		this.status = status;
		this.total = total;
	}
	
	public POModel(Long staffId, Date createdDate, String total, String status) {
		super();
		this.staffId = staffId;
		this.createdDate = createdDate;
		this.status = status;
		this.total = total;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
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
	@Override
	public String toString() {
		return "POModel [staffId=" + staffId + ", createdDate=" + createdDate + ", total=" + total + ", status="
				+ status + "]";
	}

	
}
