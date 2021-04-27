package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public interface IDiscountDetailDAO extends GenericDAO<DiscountDetailModel>{
	List<DiscountDetailModel> findAll();
	List<DiscountDetailModel> findListByDiscountId(Long discountId);

	DiscountDetailModel findOne(Long discountId, Long skuId);
	
	Long save(DiscountDetailModel model);
	DiscountDetailModel update(DiscountDetailModel update);
	void delete(Long discountId, Long skuId);
	
	void updateStatus(Long discountId, String status);

	int getTotalItem();
}
