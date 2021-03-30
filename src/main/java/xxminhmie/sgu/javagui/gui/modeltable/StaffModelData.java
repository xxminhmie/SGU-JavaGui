package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.service.impl.StaffService;

public class StaffModelData extends AbstractTableModel{
	StaffService service = new StaffService();
	List<StaffModel> data = service.findAll();
	String columnNames[] = {"ID", "First Name","Last Name",
			"Phone","Email","DOB", "Address"
	};
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public StaffService getService() {
		return this.service;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getFirstName();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getLastName();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getPhone();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getEmail();
		}
		if (columnIndex == 5) {
			return data.get(rowIndex).getAddress();
		}
		if (columnIndex == 6) {
			return data.get(rowIndex).getDob();
		}
		return null;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setFirstName((String) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setLastName((String) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setPhone((String) aValue);
		}
		if (columnIndex == 4) {
			data.get(rowIndex).setEmail((String) aValue);
		}
		if (columnIndex == 5) {
			data.get(rowIndex).setAddress((String) aValue);
		}
		if (columnIndex == 6) {
			data.get(rowIndex).setDob((java.sql.Date) aValue);
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public StaffModel getStaffModel(int rowIndex) {
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

}
