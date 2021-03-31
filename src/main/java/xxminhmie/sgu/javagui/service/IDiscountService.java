package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.DiscountModel;

public interface IDiscountService {
	List<DiscountModel> findAll();
	DiscountModel findOne(Long id);

	DiscountModel save(DiscountModel model);
	DiscountModel update(DiscountModel update);
	void delete(Long[] ids);

	List<DiscountModel> search(String str);

	int getTotalItem();
}
