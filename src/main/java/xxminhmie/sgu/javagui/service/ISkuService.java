package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.SkuModel;

public interface ISkuService {
	List<SkuModel> findAll();
	
	SkuModel findOne(Long id);
	List<SkuModel> findByProductId(Long productId);
	SkuModel findOneByColorSize(Long productId, String color, String size);

	
	SkuModel save(SkuModel skuModel);
	SkuModel update(SkuModel updateSku);
	void delete(Long[] ids);

	List<SkuModel> search(String str, Long productId);

	int getTotalItem();
	

}
