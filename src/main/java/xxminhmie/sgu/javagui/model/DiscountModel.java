package xxminhmie.sgu.javagui.model;
//DONE
import java.sql.Date;

public class DiscountModel extends AbstractModel {
	String name;
	Date startDate;
	Date endDate;
	String description;
	String status;

	
	public DiscountModel(String name, Date startDate, Date endDate, String description, String status) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.status = status;
	}
	public DiscountModel(Long id, String name, Date startDate, Date endDate, String description, String status) {
		super(id);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.status = status;
	}
	
	
	public DiscountModel() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
