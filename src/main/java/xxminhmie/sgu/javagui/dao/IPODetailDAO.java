package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.PODetailModel;

public interface IPODetailDAO extends GenericDAO<PODetailModel> {
	List<PODetailModel> findAll();
	PODetailModel findOne(Long poId, Long skuId);
	
	Long save(PODetailModel detailModel);
	void update(PODetailModel updateDetail);
	void delete(Long poId, Long skuId);

	int getTotalItem();

}
