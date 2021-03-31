package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.DiscountDAO;
import xxminhmie.sgu.javagui.model.DiscountModel;
import xxminhmie.sgu.javagui.service.IDiscountService;

public class DiscountService implements IDiscountService {
	DiscountDAO dao = new DiscountDAO();
	@Override
	public List<DiscountModel> findAll() {
		return dao.findAll();
	}

	@Override
	public DiscountModel findOne(Long id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	@Override
	public DiscountModel save(DiscountModel model) {
		Long id = dao.save(model);
		return dao.findOne(id);
	}

	@Override
	public DiscountModel update(DiscountModel update) {
		dao.update(update);
		return dao.findOne(update.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			dao.delete(id);
		}
	}

	@Override
	public List<DiscountModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
