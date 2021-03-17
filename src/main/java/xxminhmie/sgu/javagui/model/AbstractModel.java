package xxminhmie.sgu.javagui.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractModel<T> {
	
	protected Long id;
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
