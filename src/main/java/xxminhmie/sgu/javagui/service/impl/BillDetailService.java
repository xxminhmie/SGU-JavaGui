package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xxminhmie.sgu.javagui.dao.impl.BillDetailDAO;
import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.model.ProductModel;
import xxminhmie.sgu.javagui.service.IBillDetailService;

public class BillDetailService implements IBillDetailService{
	BillDetailDAO dao = new BillDetailDAO();
	@Override
	public List<BillDetailModel> findAll() {
		return dao.findAll();
	}

	@Override
	public BillDetailModel findOne(Long billId, Long skuId) {
		// TODO Auto-generated method stub
		return dao.findOne(billId, skuId);
	}

	@Override
	public void save(BillDetailModel model) {
		dao.save(model);
	}

	@Override
	public BillDetailModel update(BillDetailModel update) {
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
		List<BillDetailModel> list = this.findAll();
		List<BillDetailModel> resultList = new ArrayList<BillDetailModel>();
		
		for (BillDetailModel e : list) {
			String skuId = String.valueOf(e.getSkuId());
			String discountId = String.valueOf(e.getSkuId());
			String subTotal = String.valueOf(e.getSubTotal());


			if (skuId.contains(str) || discountId.contains(str) || subTotal.contains(str)) {
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

	@Override
	public List<BillDetailModel> findListByBillId(Long billId) {
		return dao.findListByBillId(billId);
	}
	
	public static void main(String[] args) {
	}

}
