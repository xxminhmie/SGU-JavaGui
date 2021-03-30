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
	ProductDAO productDao = new ProductDAO();



	@Override
	public List<SkuModel> findAll() {
		return skuDao.findAll();
	}

	@Override
	public SkuModel findOne(Long id) {
		SkuModel skuModel = skuDao.findOne(id);
		ProductModel productModel = productDao.findOne(skuModel.getProductId());
//		skuModel.setProductBrand(productModel.getBrand());
		return skuModel;
	}

	@Override
	public List<SkuModel> findByProductId(Long productId) {
		return skuDao.findByProductId(productId);
	}

	@Override
	public SkuModel save(SkuModel skuModel) {
//		ProductModel productModel = productDao.findOneByBrand(skuModel.getProductBrand());
//		skuModel.setProductId(productModel.getId());
//		Long skuId = skuDao.save(skuModel);
//		return skuDao.findOne(skuId);
		return null;
	}

	@Override
	public SkuModel update(SkuModel updateSku) {
//		ProductModel product = productDao.findOneByBrand(updateSku.getProductBrand());
//		updateSku.setProductId(product.getId());
//		skuDao.update(updateSku);
//		return skuDao.findOne(updateSku.getId());
		return null;
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			// 1.delete comment (khoa ngoai new_id)
			// 2.delete news
			skuDao.delete(id);
		}
	}

	@Override
	public int getTotalItem() {
		return skuDao.getTotalItem();
	}

	@Override
	public List<SkuModel> search(String str) {
		List<SkuModel> list = this.findAll();
		List<SkuModel> resultList = new ArrayList<SkuModel>();

		for (SkuModel e : list) {
			String id = String.valueOf(e.getId());
			String productId = String.valueOf(e.getProductId());
			String color = e.getColor();
			if (id.contains(str) || productId.contains(str.toLowerCase()) || color.contains(str.toLowerCase())) {
				resultList.add(e);
			}
		}
		return resultList;
	}
}
