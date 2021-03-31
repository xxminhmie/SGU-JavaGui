package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.DiscountModel;

public interface IDiscountDAO extends GenericDAO<DiscountModel>{
	List<DiscountModel> findAll();

	DiscountModel findOne(Long id);
	
	Long save(DiscountModel model);
	void update(DiscountModel update);
	void delete(Long id);
	
	int getTotalItem();
}
