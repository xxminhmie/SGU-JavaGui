package xxminhmie.sgu.javagui.dao;

import java.util.List;

import xxminhmie.sgu.javagui.model.POModel;

public interface IPODAO extends GenericDAO<POModel>{
	List<POModel> findAll();
	POModel findOne(Long id);
	POModel findOneByBrand(String brand);
	
	Long save(POModel poModel);
	void update(POModel poModel);
	void delete(Long id);

}
