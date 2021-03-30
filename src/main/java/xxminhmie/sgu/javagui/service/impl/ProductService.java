package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.ProductDAO;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.service.IProductService;

public class ProductService implements IProductService {
	ProductDAO dao = new ProductDAO();

	@Override
	public List<ProductModel> findAll() {
		return dao.findAll();
	}

	@Override
	public Long save(ProductModel productModel) {
		return dao.save(productModel);
	}

	@Override
	public ProductModel update(ProductModel updateModel) {
		dao.update(updateModel);
		return dao.findOne(updateModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductModel> search(String str) {
		List<ProductModel> list = this.findAll();
		List<ProductModel> resultList = new ArrayList<ProductModel>();
		
		for (ProductModel e : list) {
			String id = String.valueOf(e.getId());
			String brand = e.getBrand().toLowerCase();
			String name = e.getName().toLowerCase();
			if (id.contains(str) || brand.contains(str.toLowerCase()) || name.contains(str.toLowerCase())) {
				resultList.add(e);
			}
		}
		return resultList;
	}

	@Override
	public ProductModel findOne(Long id) {
		return dao.findOne(id);
	}

}
