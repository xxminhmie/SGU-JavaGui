package xxminhmie.sgu.javagui.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractModel<T> {
	
	protected Long id;
	protected java.sql.Date createdDate;
	protected java.sql.Date modifiedDate;
	protected String createdBy;
	protected String modifiedBy;
	protected Long[] ids;
	protected List<T> listResult = new ArrayList<>();
	
	public AbstractModel() {
		super();
	}
	public AbstractModel(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public java.sql.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.sql.Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.sql.Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(java.sql.Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public List<T> getListResult() {
		return listResult;
	}
	public void setListResult(List<T> listResult) {
		this.listResult = listResult;
	}
	

}
