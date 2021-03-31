package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.BillModel;

public interface IBillDAO extends GenericDAO<BillModel>{
	List<BillModel> findAll();
	
	BillModel findOne(Long id);
	
	Long save(BillModel model);
	void update(BillModel update);
	void delete(Long id);
	
	int getTotalItem();
}
