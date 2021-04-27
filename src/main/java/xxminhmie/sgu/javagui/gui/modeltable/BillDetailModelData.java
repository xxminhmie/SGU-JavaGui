package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.BillDetailModel;
import xxminhmie.sgu.javagui.model.PODetailModel;
import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.impl.BillDetailService;

public class BillDetailModelData extends AbstractTableModel{
	BillDetailService service = new BillDetailService();
	List<BillDetailModel> data = service.findAll();
	String columnNames[] = { "Bill ID", "SKU ID", "Discount ID", "Quantity", "Sub-Total" };
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public BillDetailService getService() {
		return this.service;
	}
	
	public void setData(List<BillDetailModel> data) {
		if(data==null) {
			this.data = new ArrayList<BillDetailModel>();
		}else {
			this.data = data;
		}
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getBillId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getSkuId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getDiscountId();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getQuantity();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getSubTotal();
		}
		return null;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setBillId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setSkuId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setDiscountId((Long) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setQuantity((Integer) aValue);
		}
		if (columnIndex == 4) {
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
	public BillDetailModel getModel(int rowIndex) {
		return data.get(rowIndex);
	}
	//refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
	    fireTableChanged(null);
	}
	//refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
	    fireTableChanged(null);
	}
	public void loadData(JTable table, Long billId) {
		this.data = this.service.findListByBillId(billId);
		fireTableChanged(null);
		this.setColumnWidth(table);

	}
	
	public void setColumnWidth(JTable table) {
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(40);
		columnModel.getColumn(2).setPreferredWidth(40);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);

	}
}
