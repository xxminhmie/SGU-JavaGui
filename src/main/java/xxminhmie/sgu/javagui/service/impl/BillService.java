package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.BillDAO;
import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.service.IBillService;

public class BillService implements IBillService {
	BillDAO dao = new BillDAO();

	@Override
	public List<BillModel> findAll() {
		return dao.findAll();
	}

	@Override
	public BillModel findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillModel save(BillModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillModel update(BillModel update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BillModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
