package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.RoleModel;
import xxminhmie.sgu.javagui.service.impl.RoleService;

public class RoleModelData extends AbstractTableModel {
	RoleService service = new RoleService();
	List<RoleModel> data = service.findAll();
	String columnNames[] = { "ID", "Name", "Discription" };

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

	public RoleService getService() {
		return this.service;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getName();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getDescription();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			data.get(rowIndex).setId((Long) aValue);
		}
		if (columnIndex == 1) {
			data.get(rowIndex).setName((String) aValue);
		}
		if (columnIndex == 2) {
			data.get(rowIndex).setDescription((String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);

	}
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	public RoleModel getModel(int rowIndex) {
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
