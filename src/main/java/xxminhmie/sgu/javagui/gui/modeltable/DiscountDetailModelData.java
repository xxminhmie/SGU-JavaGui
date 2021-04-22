package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.DiscountDetailModel;
import xxminhmie.sgu.javagui.service.impl.DiscountDetailService;

public class DiscountDetailModelData extends AbstractTableModel {
	DiscountDetailService service = new DiscountDetailService();
	List<DiscountDetailModel> data = service.findAll();
	String columnNames[] = { "Discount ID", "SKU ID", "Rate", "Status" };

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public DiscountDetailService getService() {
		return this.service;
	}

	public void setData(List<DiscountDetailModel> data) {
		if (data == null) {
			this.data = new ArrayList<DiscountDetailModel>();
		} else {
			this.data = data;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getDiscountId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getSkuId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getRate();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getStatus();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setDiscountId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setSkuId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setRate((Integer) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setStatus((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public DiscountDetailModel getProductModel(int rowIndex) {
		return data.get(rowIndex);
	}

	// refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
		fireTableChanged(null);
		this.setColumnWidth(table);
	}

	// refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
		fireTableChanged(null);
		this.setColumnWidth(table);

	}

	public void loadData(JTable table, Long poId) {
		this.data = this.service.findListByDiscountId(poId);
		fireTableChanged(null);
		this.setColumnWidth(table);

	}
//	public void loadData(JTable table, String poStr, String skuStr) {
//		this.data = this.service.search(poStr, skuStr);
//		fireTableChanged(null);
//		this.setColumnWidth(table);
//
//	}

	public void setColumnWidth(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
//		columnModel.getColumn(0).setPreferredWidth(40);
//		columnModel.getColumn(1).setPreferredWidth(220);
//		columnModel.getColumn(2).setPreferredWidth(80);
//		columnModel.getColumn(3).setPreferredWidth(60);
//		columnModel.getColumn(4).setPreferredWidth(160);
	}
}
