package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public interface IDiscountDetailDAO extends GenericDAO<DiscountDetailModel>{
	List<DiscountDetailModel> findAll();

	List<DiscountDetailModel> findOne(Long discountId, Long skuId);
	
	Long save(DiscountDetailModel model);
	void update(DiscountDetailModel update);
	void delete(Long discountId, Long skuId);
	
	int getTotalItem();
}
