package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.SkuModel;

public interface ISkuDAO extends GenericDAO<SkuModel> {
	List<SkuModel> findAll();

	SkuModel findOne(Long skuId);
	List<SkuModel> findByProductId(Long productId);
	SkuModel findOneByColorSize(Long productId, String color, String size);

	
	Long save(SkuModel skuModel);
	void update(SkuModel updateSku);
	void delete(Long id);

	int getTotalItem();
}
