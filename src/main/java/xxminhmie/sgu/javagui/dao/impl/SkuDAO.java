package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import xxminhmie.sgu.javagui.dao.ISkuDAO;
import xxminhmie.sgu.javagui.mapper.SkuMapper;
import xxminhmie.sgu.javagui.model.SkuModel;

public class SkuDAO extends AbstractDAO<SkuModel> implements ISkuDAO {

	@Override
	public List<SkuModel> findAll() {
		String sql = "SELECT * FROM sku;";
		return this.query(sql.toString(), new SkuMapper());
	}

	@Override
	public SkuModel findOne(Long skuId) {
		String sql = "SELECT * FROM sku WHERE id = ?";
		List<SkuModel> sku = this.query(sql, new SkuMapper(), skuId);
		return sku.isEmpty() ? null : sku.get(0);
	}

	@Override
	public List<SkuModel> findByProductId(Long productId) {
		String sql = "SELECT * FROM sku WHERE productid = ?";
		List<SkuModel> list = this.query(sql, new SkuMapper(), productId);
		return list.isEmpty() ? null : list;
	}

	@Override
	public Long save(SkuModel skuModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO sku");
		sql.append("(productid, color, size, quantity, price, importprice, status, image)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		return this.insert(sql.toString(), skuModel.getProductId(), skuModel.getColor(), skuModel.getSize(),
				skuModel.getQuantity(), skuModel.getPrice(), skuModel.getImportPrice(), skuModel.getStatus(),
				skuModel.getImage());
	}

	@Override
	public void update(SkuModel updateSku) {
		StringBuilder sql = new StringBuilder("UPDATE sku SET productid = ?, color = ?, size = ?,");
		sql.append(" quantity = ?, price = ?, importprice = ?, status = ?, image = ?,");
		sql.append(" WHERE id = ?");
		this.update(sql.toString(), updateSku.getProductId(), updateSku.getColor(), updateSku.getSize(),
				updateSku.getQuantity(), updateSku.getPrice(), updateSku.getImportPrice(), updateSku.getStatus(),
				updateSku.getImage(), updateSku.getId());
	}

	@Override
	public void delete(Long id) {
//		String sql = "DELETE * FROM sku WHERE id = ?";
//		this.update(sql, id);
		String sql = "UPDATE sku SET status = ? WHERE id = ?";
		this.update(sql, "Deleted", id);
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM sku";
		return count(sql);
	}

	@Override
	public SkuModel findOneByColorSize(Long productId, String color, String size) {
		String sql = "SELECT * FROM sku WHERE productid = ? and color = ? and size = ?;";
		List<SkuModel> sku = this.query(sql, new SkuMapper(), productId, color, size);
		return sku.isEmpty() ? null : sku.get(0);
	}

//	public static void main(String[] args) {
//		SkuDAO dao = new SkuDAO();
//		String color = "1";
//		String size = "36";
//		int qty = 1;
//		String price = "";
//		String importPrice = "";
//		String imagePath = "";
//		String status = "Actived";
//		Long productId = 14L;
//		
//		SkuModel model = new SkuModel(color, size, qty, price, importPrice, imagePath, status, productId);
//		dao.save(model);
//				
//	}

}
