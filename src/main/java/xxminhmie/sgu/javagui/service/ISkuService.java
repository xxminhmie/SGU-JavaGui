package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.paging.Pageble;

public interface ISkuService {
	List<SkuModel> findAll(Pageble pageble);
	SkuModel findOne(Long id);
	List<SkuModel> findByProductId(Long productId);
	
	SkuModel save(SkuModel skuModel);
	SkuModel update(SkuModel updateSku);
	void delete(long[] ids);

	int getTotalItem();

}
