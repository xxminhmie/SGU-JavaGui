package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.SkuModel;

public interface ISkuService {
	List<SkuModel> findAll();

	
	SkuModel findOne(Long id);
	List<SkuModel> findByProductId(Long productId);
	List<SkuModel> search(String str);
	SkuModel save(SkuModel skuModel);
	SkuModel update(SkuModel updateSku);
	void delete(long[] ids);

	int getTotalItem();

}
