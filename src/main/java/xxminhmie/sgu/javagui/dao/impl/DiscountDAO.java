package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.IDiscountDAO;
import xxminhmie.sgu.javagui.mapper.DiscountMapper;
import xxminhmie.sgu.javagui.model.DiscountModel;

public class DiscountDAO extends AbstractDAO<DiscountModel> implements IDiscountDAO{

	@Override
	public List<DiscountModel> findAll() {
		String sql = "SELECT * FROM discount;";
		return this.query(sql, new DiscountMapper());
	}

	@Override
	public DiscountModel findOne(Long id) {
		String sql = "SELECT * FROM discount WHERE id = ?";
		List<DiscountModel> model = this.query(sql, new DiscountMapper(), id);
		return model.isEmpty() ? null : model.get(0);//get id	}
	}
	

	@Override
	public Long save(DiscountModel model) {
		StringBuilder sql = new StringBuilder("INSERT INTO discount ");
		sql.append("(name, startdate, enddate, description, status) ");
		sql.append(" VALUES(?,?,?,?,?);");
		return this.insert(sql.toString(),
				model.getName(), model.getStartDate(), 
				model.getEndDate(), model.getDescription(), 
				model.getStatus());
	}

	@Override
	public void update(DiscountModel update) {
		StringBuilder sql = new StringBuilder("UPDATE discount SET name = ?, startdate = ?, ");
		sql.append("enddate = ?, description = ?, status = ? ");
		sql.append("WHERE id = ?");
		this.update(sql.toString(), 
				update.getName(), update.getStartDate(), 
				update.getEndDate(), update.getDescription(), 
				update.getStatus(), update.getId());
	}

	@Override
	public void delete(Long id) {
//		String sql = "DELETE * FROM discount WHERE id = ?";
//		this.update(sql.toString(), id);
		String sql = "UPDATE discount SET status = 'Deleted' WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
