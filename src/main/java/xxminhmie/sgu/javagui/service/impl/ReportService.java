package xxminhmie.sgu.javagui.service.impl;

import java.util.List;

import xxminhmie.sgu.javagui.dao.impl.ReportDAO;
import xxminhmie.sgu.javagui.model.ReportModel;

public class ReportService {
	ReportDAO dao = new ReportDAO();
	
	public List<ReportModel> reportMonth(int month, int year) {
		return dao.reportMonth(month, year);
	}
	
	public List<ReportModel> reportQuarter(int one, int two, int three, int year) {
		return dao.reportQuarter(one, two, three, year);
	}
	public List<ReportModel> reportYear(int year) {
		return dao.reportYear(year);
	}

}
