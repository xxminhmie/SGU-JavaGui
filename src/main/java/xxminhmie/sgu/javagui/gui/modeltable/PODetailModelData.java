package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.model.POModel;
import xxminhmie.sgu.javagui.service.impl.PODetailService;

public class PODetailModelData extends AbstractTableModel {
	PODetailService service = new PODetailService();
	List<PODetailModel> data = service.findAll();
	String columnNames[] = { "PO.ID", "SKU ID", "SupplierID", "Quantity", "Unit Price", "SubTotal" };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public PODetailService getService() {
		return this.service;
	}
	
	public void setData(List<PODetailModel> data) {
		if(data==null) {
			this.data = new ArrayList<PODetailModel>();
		}else {
			this.data = data;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getPoId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getSkuId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getSupplierId();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getQuantity();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getUnitPrice();
		}
		if (columnIndex == 5) {
			return data.get(rowIndex).getSubTotal();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setPoId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setSkuId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setSupplierId((Long) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setQuantity((Integer) aValue);
		}
		if (columnIndex == 4) {
			data.get(rowIndex).setUnitPrice((String) aValue);
		}
		if (columnIndex == 5) {
			data.get(rowIndex).setSubTotal((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public PODetailModel getProductModel(int rowIndex) {
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
		this.data = this.service.findListByPOId(poId);
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
