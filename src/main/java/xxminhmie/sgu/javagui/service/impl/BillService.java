package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.BillDAO;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.service.IBillService;

public class BillService implements IBillService {
	BillDAO dao = new BillDAO();

	@Override
	public List<BillModel> findAll() {
		return dao.findAll();
	}

	@Override
	public BillModel findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public BillModel save(BillModel model) {
		Long id = dao.save(model);
		return dao.findOne(id);
	}

	@Override
	public void update(BillModel update) {
		dao.update(update);
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BillModel> search(String str) {
		List<BillModel> list = this.findAll();
		List<BillModel> resultList = new ArrayList<BillModel>();
		
		for (BillModel e : list) {
			String billId = String.valueOf(e.getId());
			String customerId = String.valueOf(e.getCustomerId());
			String staffId = String.valueOf(e.getStaffId());
			String createdDate = e.getCreatedDate().toString();
			
			if (billId.contains(str) || customerId.contains(str) || staffId.contains(str) || createdDate.contains(str)) {
				resultList.add(e);
			}
		}
		return resultList;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
