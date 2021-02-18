package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IProductDAO;
import xxminhmie.sgu.javagui.dao.ISkuDAO;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.paging.Pageble;
import xxminhmie.sgu.javagui.service.ISkuService;

public class SkuService implements ISkuService{
	protected ISkuDAO skuDao;
	protected IProductDAO productDao;

	@Override
	public List<SkuModel> findAll(Pageble pageble) {
		return skuDao.findAll(pageble);
	}

	@Override
	public SkuModel findOne(Long id) {
		SkuModel skuModel = skuDao.findOne(id);
		ProductModel productModel = productDao.findOne(skuModel.getProductId());
		skuModel.setProductBrand(productModel.getBrand());
		return skuModel;
	}

	@Override
	public List<SkuModel> findByProductId(Long productId) {
		return skuDao.findByProductId(productId);
	}

	@Override
	public SkuModel save(SkuModel skuModel) {
		skuModel.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
		ProductModel productModel = productDao.findOneByBrand(skuModel.getProductBrand());
		skuModel.setProductId(productModel.getId());
		Long skuId = skuDao.save(skuModel);
		return skuDao.findOne(skuId);
	}

	@Override
	public SkuModel update(SkuModel updateSku) {
		SkuModel oldSku = skuDao.findOne(updateSku.getId());
		updateSku.setCreatedDate(oldSku.getCreatedDate());
		updateSku.setCreatedBy(oldSku.getCreatedBy());
		updateSku.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
		ProductModel product = productDao.findOneByBrand(updateSku.getProductBrand());
		updateSku.setProductId(product.getId());
		return skuDao.findOne(updateSku.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			//1.delete comment (khoa ngoai new_id)
			//2.delete news
			skuDao.delete(id);
		}		
	}

	@Override
	public int getTotalItem() {
		return skuDao.getTotalItem();	}

}
