package xxminhmie.sgu.javagui.model;

import java.sql.Date;

//DONE
public class BillModel extends AbstractModel {

	protected Long staffId;
	protected Long customerId;
	protected java.sql.Date createdDate;
	protected String total;

	public BillModel(Long id, Long staffId, Long customerId, Date createdDate, String total) {
		super(id);
		this.staffId = staffId;
		this.customerId = customerId;
		this.createdDate = createdDate;
		this.total = total;
	}

	public BillModel(Long staffId, Long customerId, Date createdDate, String total) {
		super();
		this.staffId = staffId;
		this.customerId = customerId;
		this.createdDate = createdDate;
		this.total = total;
	}

	public BillModel() {
	
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

}
