package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IProductDAO;
import xxminhmie.sgu.javagui.mapper.ProductMapper;
import xxminhmie.sgu.javagui.model.ProductModel;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO{

	@Override
	public List<ProductModel> findAll() {
		String sql = "SELECT * FROM product";
		return this.query(sql.toString(), new ProductMapper());
	}

	@Override
	public ProductModel findOne(Long id) {
		String sql = "SELECT * FROM  product WHERE id = ?";
		List<ProductModel> pro = this.query(sql, new ProductMapper(), id);
		return pro.isEmpty() ? null : pro.get(0);//get id
	}

	@Override
	public ProductModel findOneByBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(ProductModel productModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO product ");
		sql.append("(name, brand, description,status) ");
		sql.append("VALUES (?, ?, ?, ?)");
		return this.insert(sql.toString(),
				productModel.getName(), productModel.getBrand(), 
				productModel.getDescription(),productModel.getStatus());
	}

	@Override
	public void update(ProductModel productModel) {
		StringBuilder sql = new StringBuilder("UPDATE product SET name = ?, brand = ?, description = ?, status = ?");
		sql.append(" WHERE id = ?");
		this.update(sql.toString(), 
				productModel.getName(), productModel.getBrand(), 
				productModel.getDescription(), productModel.getStatus(),
				productModel.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "UPDATE product SET status = 'Deleted' WHERE id = ?";
		this.update(sql, id);
	}

}
