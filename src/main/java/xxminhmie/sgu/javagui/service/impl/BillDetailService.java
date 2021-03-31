package xxminhmie.sgu.javagui.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import xxminhmie.sgu.javagui.dao.impl.BillDetailDAO;
import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.service.IBillDetailService;

public class BillDetailService implements IBillDetailService{
	BillDetailDAO dao = new BillDetailDAO();
	@Override
	public List<BillDetailModel> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<BillDetailModel> findOne(Long billId, Long skuId) {
		// TODO Auto-generated method stub
		return dao.findOne(billId, skuId);
	}

	@Override
	public void save(BillDetailModel model) {
		dao.save(model);
	}

	@Override
	public List<BillDetailModel> update(BillDetailModel update) {
		dao.update(update);
		return dao.findOne(update.getBillId(), update.getSkuId());
	}

	@Override
	public void delete(Map<Long,Long> map) {
		Set<Long> set = map.keySet();
		for(Long key : set) {
			dao.delete(key, map.get(key));
		}
	}

	@Override
	public List<BillDetailModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

}
