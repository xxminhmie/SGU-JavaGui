package xxminhmie.sgu.javagui.dao.impl;

import java.util.List;

import xxminhmie.sgu.javagui.mapper.ReportMapper;
import xxminhmie.sgu.javagui.model.ReportModel;

public class ReportDAO extends AbstractDAO<ReportModel> {

	public List<ReportModel> reportMonth(int month, int year) {
		StringBuilder sql = new StringBuilder("SELECT product.id, bill.createddate, SUM(billdetail.quantity) AS SUM ");
		sql.append("FROM QLBH.bill, QLBH.billdetail, QLBH.sku, QLBH.product ");
		sql.append("WHERE bill.id = billdetail.billid ");
		sql.append("and sku.id = billdetail.skuid ");
		sql.append("and product.id = sku.productid ");
		sql.append("and month(bill.createddate) = ? ");
		sql.append("and year(bill.createddate) = ? ");
		sql.append("GROUP BY product.id, bill.createddate;");
		List<ReportModel> list = this.query(sql.toString(), new ReportMapper(), month, year);
		return list.isEmpty() ? null : list;

	}
	public List<ReportModel> reportQuarter(int one, int two, int three, int year) {
		StringBuilder sql = new StringBuilder("SELECT product.id, bill.createddate, SUM(billdetail.quantity) AS SUM ");
		sql.append("FROM QLBH.bill, QLBH.billdetail, QLBH.sku, QLBH.product ");
		sql.append("WHERE bill.id = billdetail.billid ");
		sql.append("and sku.id = billdetail.skuid ");
		sql.append("and product.id = sku.productid ");
		sql.append("and (month(bill.createddate) = ? or month(bill.createddate) = ? or month(bill.createddate) = ? )");
		sql.append("and year(bill.createddate) = ? ");
		sql.append("GROUP BY product.id, bill.createddate;");
		List<ReportModel> list = this.query(sql.toString(), new ReportMapper(), one, two, three, year);
		return list.isEmpty() ? null : list;

	}
public List<ReportModel> reportYear(int year) {
		
		StringBuilder sql = new StringBuilder("SELECT product.id, bill.createddate, SUM(billdetail.quantity) AS SUM ");
		sql.append("FROM QLBH.bill, QLBH.billdetail, QLBH.sku, QLBH.product ");
		sql.append("WHERE bill.id = billdetail.billid ");
		sql.append("and sku.id = billdetail.skuid ");
		sql.append("and product.id = sku.productid ");
		sql.append("and year(bill.createddate) = ? ");
		sql.append("GROUP BY product.id, bill.createddate;");
		List<ReportModel> list = this.query(sql.toString(), new ReportMapper(), year);
		return list.isEmpty() ? null : list;

	}
}
