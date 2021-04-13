package xxminhmie.sgu.javagui.service;

import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.model.PODetailModel;


public interface IPODetailService {
	List<PODetailModel> findAll();
	List<PODetailModel> findListByPOId(Long poId);

	PODetailModel findOne(Long poId, Long skuId);

	
	void save(PODetailModel detailModel);
	PODetailModel update(PODetailModel updateModel);
	void delete(Map<Long,Long> ids);
	
	List<PODetailModel> search(String str);

}
