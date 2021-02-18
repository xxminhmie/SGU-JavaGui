package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.ProductModel;


public interface IProductDAO extends GenericDAO<ProductModel>{
	List<ProductModel> findAll();
	ProductModel findOne(long id);
	ProductModel findOneByBrand(String brand);

}
