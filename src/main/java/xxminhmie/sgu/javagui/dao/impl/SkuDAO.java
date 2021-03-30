package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import xxminhmie.sgu.javagui.dao.ISkuDAO;
import xxminhmie.sgu.javagui.mapper.SkuMapper;
import xxminhmie.sgu.javagui.model.SkuModel;

public class SkuDAO extends AbstractDAO<SkuModel> implements ISkuDAO {

	@Override
	public SkuModel findOne(Long id) {
		String sql = "SELECT * FROM sku WHERE id = ?";
		List<SkuModel> sku = this.query(sql, new SkuMapper(), id);
		return sku.isEmpty() ? null : sku.get(0);
	}

	@Override
	public List<SkuModel> findByProductId(Long productId) {
		String sql = "SELECT * FROM sku WHERE productid = ?";
		return this.query(sql, new SkuMapper(), productId);
	}

	@Override
	public Long save(SkuModel skuModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO sku");
		sql.append("(productid, color, size, quantity, price, sellprice, status, image)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return this.insert(sql.toString(), skuModel.getProductId(), skuModel.getColor(), skuModel.getSize(),
				skuModel.getQuantity(), skuModel.getPrice(), skuModel.getImportPrice(), skuModel.getStatus(),
				skuModel.getImage());
	}

	@Override
	public void update(SkuModel updateSku) {
		StringBuilder sql = new StringBuilder("UPDATE sku SET productid = ?, color = ?, size = ?,");
		sql.append(" quantity = ?, price = ?, sellprice = ?, status = ?, image = ?,");
		sql.append(" WHERE id = ?");
		this.update(sql.toString(), updateSku.getProductId(), updateSku.getColor(), updateSku.getSize(),
				updateSku.getQuantity(), updateSku.getPrice(), updateSku.getImportPrice(), updateSku.getStatus(),
				updateSku.getImage(), updateSku.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM sku WHERE id = ?";
		this.update(sql, id);
		;
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM sku";
		return count(sql);
	}

	@Override
	public List<SkuModel> findAll() {
		String sql = "SELECT * FROM sku";
		return this.query(sql.toString(), new SkuMapper());
	}

}
