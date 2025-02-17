package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.SkuModel;

public interface ISkuService {
	List<SkuModel> findAll();
	
	SkuModel findOne(Long id);
	List<SkuModel> findByProductId(Long productId);
	List<SkuModel> findByStatus(String status);

	SkuModel findOneByColorSize(Long productId, String color, String size);
	

	
	SkuModel save(SkuModel skuModel);
	SkuModel update(SkuModel updateSku);
	void delete(Long[] ids);
	void deleteByProductId(Long id);

	List<SkuModel> search(String str, Long productId);
	List<SkuModel> search(Long from, Long to, Long productId);

	

	int getTotalItem();
	

}
