package xxminhmie.sgu.javagui.service;

import java.util.List;
import java.util.Map;

import xxminhmie.sgu.javagui.model.BillDetailModel;


public interface IBillDetailService {
	List<BillDetailModel> findAll();
	BillDetailModel findOne(Long billId, Long skuId);
	List<BillDetailModel> findListByBillId(Long billId);


	void save(BillDetailModel model);
	BillDetailModel update(BillDetailModel update);
	void delete(Map<Long,Long> map);

	List<BillDetailModel> search(String str);

	int getTotalItem();


}
