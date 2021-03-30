package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.PODAO;
import xxminhmie.sgu.javagui.model.POModel;
import xxminhmie.sgu.javagui.service.IPOService;

public class POService implements IPOService{
	PODAO dao = new PODAO();
	
	@Override
	public List<POModel> findAll() {
		return dao.findAll();
	}

	@Override
	public POModel findOne(Long id) {
		return dao.findOne(id);
	}

	@Override
	public Long save(POModel poModel) {
		return dao.save(poModel);
	}

	@Override
	public POModel update(POModel updateModel) {
		dao.update(updateModel);
		return dao.findOne(updateModel.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id : ids) {
			dao.delete(id);
		}
	}

	@Override
	public List<POModel> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
