package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.BillModel;

public interface IBillService {
	List<BillModel> findAll();

	BillModel findOne(Long id);

	BillModel save(BillModel model);

	BillModel update(BillModel update);

	void delete(Long[] ids);

	List<BillModel> search(String str);

	int getTotalItem();

}
