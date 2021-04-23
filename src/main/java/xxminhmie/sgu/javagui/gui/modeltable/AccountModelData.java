package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.AccountModel;
import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.impl.AccountService;


public class AccountModelData extends AbstractTableModel{
	AccountService service = new AccountService();
	List<AccountModel> data = service.findAll();
	String columnNames[] = { "ID", "RoleID","StaffID","Username" };
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
	public AccountService getService() {
		return this.service;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getRoleId();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getStaffId();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getUsername();
		}
		
		return null;
	}
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setRoleId((Long) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setStaffId((Long) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setUsername((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);

	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public AccountModel getModel(int rowIndex) {
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
