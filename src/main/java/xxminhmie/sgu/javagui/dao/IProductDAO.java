package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.ProductModel;


public interface IProductDAO extends GenericDAO<ProductModel>{
	List<ProductModel> findAll();
	ProductModel findOne(Long id);
	ProductModel findOneByBrand(String brand);
	
	Long save(ProductModel productModel);
	void update(ProductModel productModel);
	void delete(Long id);

}
