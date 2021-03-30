package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.ProductModel;

public interface IProductService {
	List<ProductModel> findAll();
	ProductModel findOne(Long id);
	
	Long save(ProductModel productModel);
	ProductModel update(ProductModel updateModel);
	void delete(Long[] ids);
	
	List<ProductModel> search(String str);
	
}
