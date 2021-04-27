package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.BillDetailModel;

public interface IBillDetailDAO extends GenericDAO<BillDetailModel>{
	List<BillDetailModel> findAll();

	BillDetailModel findOne(Long billId, Long skuId);
	List<BillDetailModel> findListByBillId(Long billId);

	
	Long save(BillDetailModel model);
	void update(BillDetailModel update);
	void delete(Long billId, Long skuId);
	
	int getTotalItem();
}
