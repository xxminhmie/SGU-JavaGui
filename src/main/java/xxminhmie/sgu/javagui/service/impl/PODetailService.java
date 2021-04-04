package xxminhmie.sgu.javagui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xxminhmie.sgu.javagui.dao.impl.PODetailDAO;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.model.SkuModel;
import xxminhmie.sgu.javagui.service.IPODetailService;

public class PODetailService implements IPODetailService {
	PODetailDAO dao = new PODetailDAO();

	@Override
	public List<PODetailModel> findAll() {
		return dao.findAll();
	}

	@Override
	public PODetailModel findOne(Long poId, Long skuId) {
		// TODO Auto-generated method stub
		return dao.findOne(poId, skuId);
	}

	@Override
	public void save(PODetailModel detailModel) {
		// TODO Auto-generated method stub
		dao.save(detailModel);
	}

	@Override
	public PODetailModel update(PODetailModel updateModel) {
		dao.update(updateModel);
		return dao.findOne(updateModel.getPoId(), updateModel.getSkuId());
	}

	@Override
	public void delete(Map<Long, Long> map) {
		Set<Long> set = map.keySet();
		for (Long key : set) {
			dao.delete(key, map.get(key));
		}
	}

	@Override
	public List<PODetailModel> search(String str) {
		// TODO Auto-generated method stub
		List<PODetailModel> list = this.findAll();
		List<PODetailModel> resultList = new ArrayList<PODetailModel>();

		for (PODetailModel e : list) {
			String poId = String.valueOf(e.getPoId());
//			String skuId = String.valueOf(e.getSkuId());

			if (poId.contains(str)) {
				resultList.add(e);
			}
		}
		return resultList;
	}

//	public List<PODetailModel> search(String poStr, String skuStr) {
//		List<PODetailModel> list = this.findAll();
//		List<PODetailModel> resultList = new ArrayList<PODetailModel>();
//
//		for (PODetailModel e : list) {
//			String poId = String.valueOf(e.getPoId());
//			String skuId = String.valueOf(e.getSkuId());
//
//			if (poId.contains(poStr) || skuId.contains(skuStr.toLowerCase())) {
//				resultList.add(e);
//			}
//		}
//		return resultList;
//	}

}
