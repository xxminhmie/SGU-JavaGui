package xxminhmie.sgu.javagui.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import xxminhmie.sgu.javagui.dao.impl.DiscountDetailDAO;
import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.service.IDiscountDetailService;

public class DiscountDetailService implements IDiscountDetailService {
	DiscountDetailDAO dao = new DiscountDetailDAO();
	@Override
	public List<DiscountDetailModel> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public DiscountDetailModel findOne(Long discountId, Long skuId) {
		return dao.findOne(discountId, skuId);
	}

	@Override
	public Long save(DiscountDetailModel model) {
		return dao.save(model);
	}

	@Override
	public DiscountDetailModel update(DiscountDetailModel update) {
		return dao.update(update);
	}

	@Override
	public void delete(Map<Long,Long> map) {
		Set<Long> set = map.keySet();
		for(Long key : set) {
			dao.delete(key, map.get(key));
		}		
	}

	@Override
	public List<DiscountDetailModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DiscountDetailModel> findListByDiscountId(Long discountId) {
		return dao.findListByDiscountId(discountId);
	}

	@Override
	public void updateStatus(Long discountId, String status) {
		dao.updateStatus(discountId, status);
	}

}
