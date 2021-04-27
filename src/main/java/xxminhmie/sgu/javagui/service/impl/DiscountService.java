package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.DiscountDAO;
import xxminhmie.sgu.javagui.model.DiscountModel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.service.IDiscountService;

public class DiscountService implements IDiscountService {
	DiscountDAO dao = new DiscountDAO();
	@Override
	public List<DiscountModel> findAll() {
		return dao.findAll();
	}

	@Override
	public DiscountModel findOne(Long id) {
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
			DiscountDetailService detailService = new DiscountDetailService();
			detailService.updateStatus(id, "Deleted");

		}
	}

	@Override
	public List<DiscountModel> search(String str) {
		List<DiscountModel> list = this.findAll();
		List<DiscountModel> resultList = new ArrayList<DiscountModel>();

		for (DiscountModel e : list) {
			String id = String.valueOf(e.getId());
			String startDate = e.getStartDate().toString();
			String endDate = e.getEndDate().toString();
			String status = e.getStatus();

			if (id.contains(str) || startDate.contains(str) || endDate.contains(str) || status.contains(str)) {
				resultList.add(e);
			}
		}
		return resultList;
	}

	@Override
	public int getTotalItem() {
		return 0;
	}

}
