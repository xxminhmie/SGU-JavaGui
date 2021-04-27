package xxminhmie.sgu.javagui.service;

import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public interface IDiscountDetailService {
	List<DiscountDetailModel> findAll();
	List<DiscountDetailModel> findListByDiscountId(Long discountId);


	DiscountDetailModel findOne(Long discountId, Long skuId);

	Long save(DiscountDetailModel model);
	DiscountDetailModel update(DiscountDetailModel update);
	void delete(Map<Long,Long> map);
	
	void updateStatus(Long discountId, String status);


	List<DiscountDetailModel> search(String str);

	int getTotalItem();

}
