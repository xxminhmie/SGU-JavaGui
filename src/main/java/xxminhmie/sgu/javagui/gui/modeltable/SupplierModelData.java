
package xxminhmie.sgu.javagui.gui.modeltable;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import xxminhmie.sgu.javagui.model.StaffModel;
import xxminhmie.sgu.javagui.model.SupplierModel;
import xxminhmie.sgu.javagui.service.impl.StaffService;
import xxminhmie.sgu.javagui.service.impl.SupplierService;

public class SupplierModelData extends AbstractTableModel {
	SupplierService service = new SupplierService();
	List<SupplierModel> data = service.findAll();
	String columnNames[] = { "ID", "Name", "Phone", "Email", "Address" };

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

	public SupplierService getService() {
		return this.service;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0) {
			return data.get(rowIndex).getId();
		}
		if (columnIndex == 1) {
			return data.get(rowIndex).getName();
		}
		if (columnIndex == 2) {
			return data.get(rowIndex).getPhone();
		}
		if (columnIndex == 3) {
			return data.get(rowIndex).getEmail();
		}
		if (columnIndex == 4) {
			return data.get(rowIndex).getAddress();
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
			data.get(rowIndex).setPhone((String) aValue);
		}
		if (columnIndex == 3) {
			data.get(rowIndex).setEmail((String) aValue);
		}
		if (columnIndex == 4) {
			data.get(rowIndex).setAddress((String) aValue);
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public SupplierModel getStaffModel(int rowIndex) {
		return data.get(rowIndex);
	}

	// refresh data from database
	public void loadData(JTable table) {
		this.data = this.service.findAll();
		fireTableChanged(null);
	}

	// refresh data from list
	public void loadData(JTable table, String str) {
		this.data = this.service.search(str);
		fireTableChanged(null);
	}

}
