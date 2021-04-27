package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import xxminhmie.sgu.javagui.model.BillModel;
import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.impl.BillService;

public class BillModelData extends AbstractTableModel {
	BillService service = new BillService();
	List<BillModel> data = service.findAll();
	String columnNames[] = { "ID", "StaffId", "CustomerId", "Created Date", "Total" };

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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getStaffId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getCustomerId();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getCreatedDate();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getTotal();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setStaffId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setCustomerId((Long) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setCreatedDate((java.sql.Date) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setTotal((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);

	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public BillModel getModel(int rowIndex) {
		return data.get(rowIndex);
	}

	// refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
		setColumnWidth(table);
		fireTableChanged(null);
	}

	// refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
		setColumnWidth(table);
		fireTableChanged(null);
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
