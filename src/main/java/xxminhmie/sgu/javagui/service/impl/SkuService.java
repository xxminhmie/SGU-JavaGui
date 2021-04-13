package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.ProductDAO;
import xxminhmie.sgu.javagui.dao.impl.SkuDAO;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.ISkuService;

public class SkuService implements ISkuService {
	SkuDAO skuDao = new SkuDAO();
	
	@Override
	public List<SkuModel> findAll() {
		return skuDao.findAll();
	}

	@Override
	public SkuModel findOne(Long id) {
		SkuModel skuModel = skuDao.findOne(id);
		return skuModel;
	}

	@Override
	public List<SkuModel> findByProductId(Long productId) {
		return skuDao.findByProductId(productId);
	}

	@Override
	public SkuModel save(SkuModel skuModel) {
		Long skuId = skuDao.save(skuModel);
		return skuDao.findOne(skuId);
	}

	@Override
	public SkuModel update(SkuModel updateSku) {
		skuDao.update(updateSku);
		return skuDao.findOne(updateSku.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for (long id : ids) {
			skuDao.delete(id);
		}
	}

	@Override
	public int getTotalItem() {
		return skuDao.getTotalItem();
	}

	@Override
	public List<SkuModel> search(String str, Long productId) {
		List<SkuModel> list = this.findAll();
		List<SkuModel> resultList = new ArrayList<SkuModel>();

		for (SkuModel e : list) {
			String id = String.valueOf(e.getId());
			String color = e.getColor();
			if (id.contains(str) || color.toLowerCase().contains(str.toLowerCase())) {
				if(e.getProductId() == productId) {
					resultList.add(e);
				}
			}
		}
		return resultList;
	}

	@Override
	public SkuModel findOneByColorSize(Long productId, String color, String size) {
		return skuDao.findOneByColorSize(productId, color, size);
	}
}
