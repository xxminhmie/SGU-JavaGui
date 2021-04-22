package xxminhmie.sgu.javagui.service;

import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;

public interface IDiscountDetailService {
	List<DiscountDetailModel> findAll();
	List<DiscountDetailModel> findListByDiscountId(Long discountId);

	List<DiscountDetailModel> findOne(Long discountId, Long skuId);

	void save(DiscountDetailModel model);
	void update(DiscountDetailModel update);
	void delete(Map<Long,Long> map);

	List<DiscountDetailModel> search(String str);

	int getTotalItem();

}
