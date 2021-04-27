package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.ReportModel;


public class ReportModelData extends AbstractTableModel {
	List<ReportModel> data;
//	String columnNames[] = { "Product ID", "Quantity Total", "Price Total"};
	String columnNames[] = { "Product ID", "Quantity Total"};


	public ReportModelData(List<ReportModel> data) {
		this.data = data;
	}
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getProductId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getQuantity();
		}
//		if (columnIndex == 2) {
//			return data.get(rowIndex).getTotal();
//		}
		
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setProductId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setQuantity((Integer) aValue);
		}
//		if (columnIndex == 2) {
//			data.get(rowIndex).setTotal((String) aValue);
//		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


	// refresh data from database
	public void loadData(JTable table) {
		fireTableChanged(null);
	}
	public void loadData(JTable table, List<ReportModel> list) {
		this.data = list;
		fireTableChanged(null);
	}



}
