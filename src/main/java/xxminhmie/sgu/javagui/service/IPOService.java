package xxminhmie.sgu.javagui.service;

import java.util.List;

import xxminhmie.sgu.javagui.model.POModel;


public interface IPOService {
	List<POModel> findAll();
	POModel findOne(Long id);
	
	Long save(POModel poModel);
	POModel update(POModel updateModel);
	void delete(Long[] ids);
	
	List<POModel> search(String str);

}
